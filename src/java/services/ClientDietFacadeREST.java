/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.logging.Logger;
import ejb.ClientDietInterface;
import entities.ClientDiet;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
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
 * @author JulenB
 * RESTful Service of the clientdiets
 */
@Path("clientdiet")
public class ClientDietFacadeREST {

    /**
     * EJB object implementing business logic.
     */
    @EJB
    private ClientDietInterface ejb;
    
    private static final Logger LOGGER=Logger.getLogger(TipFacadeREST.class.getName());
    
    
    /**
     * POST method to create clientDiet relations: uses insertClientDiet method.
     * @param clientDiet  The clientDiet containing data.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void create(ClientDiet clientDiet) {
        try {
            LOGGER.log(Level.INFO, "Creating clientDiet {0}", clientDiet.getClientDietId());
            ejb.insertClientDiet(clientDiet);
        } catch (CreateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }

    /**
     * PUT method to modify clientDiet relations: uses updateClientDiet method.
     * @param clientDiet  The clientDiet containing data.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(ClientDiet clientDiet) {
        try {
            LOGGER.log(Level.INFO, "Updating clientDiet {0}", clientDiet.getClientDietId());
            ejb.updateClientDiet(clientDiet);
        } catch (UpdateException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }
    
    /**
     * DELETE method to remove clientDiet relations: uses remove method.
     * @param user_id  The clientDiet user_id.
     */
    @DELETE
    @Path("findClientDietsRelation/{user_id}")
    public void remove(@PathParam("user_id") Integer user_id) {
        try {
            LOGGER.log(Level.INFO, "Removing clientDiet {0}", user_id);
            ejb.removeClientDiet(ejb.findClientDietsRelation(user_id));
        } catch (DeleteException | ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }

    
    /**
     * GET method for getting a list of client diets by its id: uses findAllClientDiets method.
     * @param user_id  The client_id of the client.
     * @return A list of diets.
     */
    @GET
    @Path("findAllClientDiets/{user_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ClientDiet> find(@PathParam("user_id") Integer user_id) {
        try {
            LOGGER.log(Level.INFO, "Finding all clientDiets", user_id);
            return ejb.findAllClientDiets(user_id);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }
    
    
    /**
     * GET method for getting a list of client diets by its id and which are not active: uses findClientDietsRelation method.
     * @param user_id  The client_id of the client.
     * @return A list of diets.
     */
    @GET
    @Path("findClientDietsRelation/{user_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ClientDiet> findClientDietsRelation(@PathParam("user_id") Integer user_id) {
        try {
            LOGGER.log(Level.INFO, "Finding all clientDiets that are not active", user_id);
            return ejb.findClientDietsRelation(user_id);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }
    
    
    /**
     * GET method for getting a list of clients and diets: uses findAll_C_D method.
     * @return A list of diets and clients.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<ClientDiet> findAll_C_D() {
        try {
            LOGGER.log(Level.INFO, "Finding all clientsDiets");
            return ejb.findAll_C_D();
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }
    
    
    /**
     * GET method for getting a list of client diets by its id and which are active: uses findClientDietsRelation method.
     * @param user_id  The client_id of the client.
     * @return A list of diets.
     */
    @GET
    @Path("findClientDietRelationIsActive/{user_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ClientDiet> findClientDietRelationIsActive(@PathParam("user_id") Integer user_id) {
        try {
            LOGGER.log(Level.INFO, "Finding all clientDiets that are active", user_id);
            return ejb.findClientDietRelationIsActive(user_id);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }
}
