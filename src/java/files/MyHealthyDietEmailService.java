package files;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 
 * @author Julen B
 * A Email service that sends an email to a user.
 */

public class MyHealthyDietEmailService {
    private static final Logger LOGGER=Logger.getLogger(MyHealthyDietEmailService.class.getName());
    private Symmetric sym = new Symmetric();
    private String transmitter;
    private String emailkey;
    /**
     * Method that sends a email to the user.
     * @param receiver user that request it.
     * @param password
     */

    public void sendEmail(String receiver, String password, String body, String subject) {
        try {
            Properties properties = new Properties();
            InputStream input = new ByteArrayInputStream(sym.decryptText("abcd*1234"));
            properties.load(input);
            transmitter = properties.getProperty("TRANSMITTER");
            emailkey = properties.getProperty("EMAILKEY");
        } catch (IOException ex) {
            Logger.getLogger(MyHealthyDietEmailService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        Properties prop = System.getProperties();
        //Using username and password authentication
        prop.put("mail.smtp.auth", "true");
        //To connect securely to the SMTP server
        prop.put("mail.smtp.starttls.enable", "true"); 
        //Google SMTP server
        prop.put("mail.smtp.host", "smtp.gmail.com");
        //Google's secure SMTP port
        prop.put("mail.smtp.port", "587");
        //Account mail
        prop.put("mail.smtp.user", transmitter);
        //Account key
        prop.put("mail.smtp.clave", emailkey);
        
        
        Session session = Session.getDefaultInstance(prop);
        MimeMessage message = new MimeMessage(session);

        try {
            LOGGER.info("Sending password recovery email.");
            message.setFrom(new InternetAddress(transmitter));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", transmitter, emailkey);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            LOGGER.severe(me.getMessage());
        }
    }
    
    /**
     * 
     * @return password thats generated randomly
     */
    public static StringBuilder generateRandomPassword() {
        LOGGER.info("Generating new password.");
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$^&*()-_=+";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < 15; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }
        return password;
        
        
    }
}
