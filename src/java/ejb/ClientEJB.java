/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import cryptography.HashMD5;
import files.MyHealthyDietEmailService;
import entities.Client;
import entities.StatusEnum;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import files.Asymmetric;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.DatatypeConverter;

/**
 * This is the stateless EJB that implements the ClientInterface
 *
 * @author Sendoa
 */
@Stateless
public class ClientEJB implements ClientInterface {

    /**
     * EntityManager for MyHealthyDietPU persistence unit.
     */
    @PersistenceContext(unitName = "MyHealthyDietPU")
    private EntityManager em;

    /**
     * This method creates a new Client
     *
     * @param client The client entity containing the data
     * @throws CreateException Exception thrown when any error ocurrs during
     * creation
     */
    @Override
    public void createClient(Client client) throws CreateException {
        try {
            byte[] passwordBytes = new Asymmetric().decrypt(DatatypeConverter.parseHexBinary(client.getPassword()));
            client.setPassword(HashMD5.hashText(new String(passwordBytes)));
            client.setUser_id(null);
            em.persist(client);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * This method updates a clients password
     *
     * @param client The client entity containing all the new data
     * @throws UpdateException Exception thrown when any error ocurrs during the
     * update
     */
    @Override
    public void updateClientPassword(Client client) throws UpdateException {
        try {
            if (!em.contains(client)) {
                MyHealthyDietEmailService emailService = new MyHealthyDietEmailService();
                String body = "Dear " + client.getFullName() + ",\n"
                        + "\n"
                        + "We hope this message finds you well. We have received a request to change the password for your account on our app. If you did not request this, please let us know immediately.\n"
                        + "\n"
                        + "If you have any issues or questions, please don't hesitate to reach out to our support team via myhealthydiet.jhms@gmail.com.\n"
                        + "\n"
                        + "Best regards,\n"
                        + "The MyHealthyDiet Team\n"
                        + "\n"
                        + "Please note that this is an automated message and replies to this email will not be read. If you have any further questions, please contact customer service.";
                String subject = "Password Changed";
                emailService.sendEmail(client.getEmail(), null, body, subject);
                byte[] passwordBytes = new Asymmetric().decrypt(DatatypeConverter.parseHexBinary(client.getPassword()));
                client.setPassword(HashMD5.hashText(new String(passwordBytes)));
                em.merge(client);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * This method updates a clients password
     *
     * @param client The client entity containing all the new data
     * @throws UpdateException Exception thrown when any error ocurrs during the
     * update
     */
    @Override
    public void updateClient(Client client) throws UpdateException {
        try {
            client.setPassword(findClientById(client.getUser_id()).getPassword());
            if (!em.contains(client)) {
                em.merge(client);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * This method removes a client
     *
     * @param client The client entity that is going to be removed
     * @throws DeleteException Exception thrown when any error ocurrs during the
     * removal
     */
    @Override
    public void removeClient(Client client) throws DeleteException {
        try {
            em.remove(em.merge(client));
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * This method finds all the clients
     *
     * @return a list with all the clients
     * @throws ReadException Exception thrown when any error ocurrs during the
     * query
     */
    @Override
    public List<Client> findAllClient() throws ReadException {
        List<Client> clients;

        try {
            clients = em.createNamedQuery("findAllClient").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }

        return clients;
    }

    /**
     * This method finds a client using an id
     *
     * @param id the id of the client
     * @return the client if it finds one
     * @throws ReadException Exception thrown when any error ocurrs during the
     * query
     */
    @Override
    public Client findClientById(Integer id) throws ReadException {
        Client client;

        try {
            client = em.find(Client.class, id);
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }

        return client;
    }

    /**
     * This method finds a client using a string
     *
     * @param usrValue this string can be anything and is going to be used to
     * find the client as his login, email or full name
     * @return the client if it finds one
     * @throws ReadException Exception thrown when any error ocurrs during the
     * query
     */
    @Override
    public List<Client> findClientBySearch(String usrValue) throws ReadException {
        List<Client> clients;

        try {
            clients = em.createNamedQuery("findClientBySearch").setParameter("usrValue", "%" + usrValue + "%").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }

        return clients;
    }

    /**
     * This method finds all client using their status
     *
     * @param status The status of the client (ENABLED or DISABLED)
     * @return The clients that have that status
     * @throws ReadException Exception thrown when any error ocurrs during the
     * query
     */
    @Override
    public List<Client> findClientByStatus(StatusEnum status) throws ReadException {
        List<Client> clients;

        try {
            clients = em.createNamedQuery("findClientByStatus").setParameter("usrStatus", status).getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }

        return clients;
    }

    /**
     * This method finds a single client using login
     *
     * @param login the login or username of the client
     * @return The client with that login
     * @throws ReadException Exception thrown when any error ocurrs during the
     * query
     */
    @Override
    public Client findClientByLogin(String login) throws ReadException {
        Client client;

        try {
            client = (Client) em.createNamedQuery("findClientByLogin").setParameter("usrLogin", login).getSingleResult();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }

        return client;
    }

    /**
     * This method finds a single client using email
     *
     * @param email the email of the client
     * @return The client with that email
     * @throws ReadException Exception thrown when any error ocurrs during the
     * query
     */
    @Override
    public Client findClientByEmail(String email) throws ReadException {
        Client client;

        try {
            client = (Client) em.createNamedQuery("findClientByEmail").setParameter("usrEmail", email).getSingleResult();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }

        return client;
    }

    /**
     * This method generates a new password for the user and then sends it to
     * the client via email
     *
     * @param client The client who is going to have its password reset
     * @throws UpdateException Exception thrown when any error ocurrs during the
     * update
     */
    @Override
    public void recoverPassword(Client client) throws UpdateException {
        try {
            if (!em.contains(client)) {
                MyHealthyDietEmailService emailService = new MyHealthyDietEmailService();
                String password = emailService.generateRandomPassword().toString();
                String body = "Dear customer,\n"
                        + "\n"
                        + "We have received a request to reset the password for your account.\n"
                        + "\n"
                        + "To reset your password. Use this password the next time you log in into the app.\n"
                        + "\n"
                        + password
                        + "\n"
                        + "\n"
                        + "If you did not initiate this request, please contact our customer service team immediately at myhealthydiet.jhms@gmail.com. We take the security of your account very seriously and will assist you in resolving any unauthorized access to your account.\n"
                        + "\n"
                        + "Thank you for choosing MyHealthyDiet for your needs. We appreciate your business and look forward to helping you with any future needs.\n"
                        + "\n"
                        + "Sincerely,\n"
                        + "The MyHealthyDiet Team\n"
                        + "\n"
                        + "Please note that this is an automated message and replies to this email will not be read. If you have any further questions, please contact customer service.";
                String subject = "Password Recovery for Your Account";
                emailService.sendEmail(client.getEmail(), password, body, subject);
                client.setPassword(HashMD5.hashText(password));
                em.merge(client);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

}
