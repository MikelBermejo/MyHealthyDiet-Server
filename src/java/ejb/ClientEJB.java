/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Client;
import entities.StatusEnum;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is the stateless EJB that implements the ClientInterface
 *
 * @author 2dam
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
            em.persist(client);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * This method updates a client
     *
     * @param client The client entity containing all the new data
     * @throws UpdateException Exception thrown when any error ocurrs during the
     * update
     */
    @Override
    public void updateClient(Client client) throws UpdateException {
        try {
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
        } catch (Exception e){
            throw new ReadException(e.getMessage());
        }
        
        return client;
    }

}
