/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import ejb.ClientInterface;
import ejb.TipInterface;
import entities.Client;
import entities.StatusEnum;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Sendoa
 */
@Path("client")
public class ClientFacadeREST {

    /**
     * EJB object implementing business logic.
     */
    @EJB
    private ClientInterface ejb;

    private Logger LOGGER = Logger.getLogger(ClientFacadeREST.class.getName());

    /**
     * POST method to create a client
     *
     * @param entity the Client object containing the data
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Client entity) {
        try {
            LOGGER.log(Level.INFO, "Creating client {0}", entity.getUser_id());
            entity.setUser_id(null);
            ejb.createClient(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * PUT method to update a client except its password
     *
     * @param entity the Client object containing all the new data
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Client entity) {
        try {
            LOGGER.log(Level.INFO, "Updating client {0}", entity.getUser_id());
            ejb.updateClient(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * PUT method to update a clients password
     * 
     * @param entity 
     */
    @PUT
    @Path("editPassword")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void editPassword(Client entity) {
        try {
            LOGGER.log(Level.INFO, "Updating client {0}", entity.getUser_id());
            ejb.updateClientPassword(entity);
        } catch(UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    /**
     * PUT method to recover a clients password
     *
     * @param entity the Client object that's going to have their password
     * changed
     */
    @PUT
    @Path("recoverPassword")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void recoverPassword(Client entity) {
        try {
            LOGGER.log(Level.INFO, "Updating client {0}", entity.getUser_id());
            ejb.recoverPassword(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * DELETE method to remove a client
     *
     * @param id the id of the tip
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Removeing client {0}", id);
            ejb.removeClient(ejb.findClientById(id));
        } catch (ReadException | DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method to find a client by its id
     *
     * @param id The id of the client
     * @return A client object
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Client findClientById(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Finding client by id");
            Client client = ejb.findClientById(id);
            client.setPassword(null);
            return client;
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method to find cliebnts by their status
     *
     * @param status the status
     * @return A list of Client object
     */
    @GET
    @Path("/status/{status}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Client> findClientByStatus(@PathParam("status") StatusEnum status) {
        try {
            LOGGER.log(Level.INFO, "Finding client by status");
            List<Client> clients = ejb.findClientByStatus(status);
            for (Client client : clients){
                client.setPassword(null);
            }
            return clients;
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method to find some client
     *
     * @param usrValue A string used to finds client
     * @return A list of Client objects
     */
    @GET
    @Path("/search/{usrValue}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Client> findClientBySearch(@PathParam("usrValue") String usrValue) {
        try {
            LOGGER.log(Level.INFO, "Finding client by search");
            List<Client> clients = ejb.findClientBySearch(usrValue);
            for (Client client : clients){
                client.setPassword(null);
            }
            return clients;
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * GET method to find all the clients
     *
     * @return A list of Client objects
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Client> findAll() {
        try {
            LOGGER.log(Level.INFO, "Finding all clients");
            List<Client> clients = ejb.findAllClient();
            for (Client client : clients){
                client.setPassword(null);
            }
            return clients;
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("/login/{usrLogin}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Client findClientByLogin(@PathParam("usrLogin") String usrLogin) {
        try {
            LOGGER.log(Level.INFO, "Finding client by login");
            Client client = ejb.findClientByLogin(usrLogin);
            client.setPassword(null);
            return client;
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
}
