package files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author HaizeaF
 * This class generates a file with the email credentials encrypted symmetrically and decrypts it.
 */
public class Symmetric {

    static String sSalt = "abcdefghijklmnop";
    private static byte[] salt = sSalt.getBytes();
    private static final Logger LOGGER = Logger.getLogger("Symmetric.class");

    /**
     * @param nKey The key used to create the key spec.
     * @param data Email credentials.
     * This method generates the file encrypted and saves the key.
     */
    public void encryptText(String nKey, String data) {
        LOGGER.info("Generating and encrypting the file.");
        String ret = null;
        KeySpec keySpec = null;
        SecretKeyFactory secretKeyFactory = null;
        try {
            keySpec = new PBEKeySpec(nKey.toCharArray(), salt, 65536, 128);
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, "AES");
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] encodedMessage = c.doFinal(data.getBytes());
            byte[] iv = c.getIV();
            fileWriter("./src/java/files/PrivateSymmetric.key", iv);
            fileWriter("./src/java/files/emailCredentials.properties", encodedMessage);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
           LOGGER.severe(e.getMessage());
        }
    }
    
    /**
     * @param nKey The key needed to decrypt the file.
     * @return The file of the email credentials decrypted.
     */
    public byte[] decryptText(String nKey) {
        LOGGER.info("Decrypting the file.");
        byte[] decodedMessage = null;
        byte[] contentIv = fileReader(Symmetric.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/../PrivateSymmetric.key");
        byte[] contentCredentials = fileReader(Symmetric.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/../emailCredentials.properties");
        KeySpec keySpec = null;
        SecretKeyFactory secretKeyFactory = null;
        try {
            keySpec = new PBEKeySpec(nKey.toCharArray(), salt, 65536, 128);
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(Arrays.copyOfRange(contentIv, 0, contentIv.length));
            cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);
            decodedMessage = cipher.doFinal(Arrays.copyOfRange(contentCredentials, 0, contentCredentials.length));
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            LOGGER.severe(e.getMessage());
        }
        return decodedMessage;
    }
    
    /**
     * @param path The path for the file.
     * @param text The text the file contains.
     * Method to write a file.
     */
    private void fileWriter(String path, byte[] text) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(text);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    /**
     * @param path The path where the file is.
     * @return The bytes of the file.
     */
    private byte[] fileReader(String path) {
        byte ret[] = null;
        File file = new File(path);
        try {
            ret = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        return ret;
    }
    
    public static void main(String[] args) {
        Symmetric sym = new Symmetric();
        sym.encryptText("abcd*1234", "TRANSMITTER=myhealthydiet.jhms@gmail.com"
                + "\nEMAILKEY=ehlvzgjvtvtiuaqb");
        LOGGER.info("Symmetric key and email credentials file generated.");
    }
}
