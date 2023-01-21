/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import ejb.TipEJB;
import ejb.TipInterface;
import entities.Tip;
import entities.TipTypeEnum;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Sendoa
 */
@Path("tip")
public class TipFacadeREST {

    /**
     * EJB object implementing business logic.
     */
    @EJB
    private TipInterface ejb;

    private Logger LOGGER=Logger.getLogger(TipFacadeREST.class.getName());

    /**
     * POST method to create tips
     * @param entity The tip object containing the data
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Tip entity) {
        try {
            LOGGER.log(Level.INFO, "Creating tip {0}", entity.getTip_id());
            ejb.createTip(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());  
        }
    }

    /**
     * PUT method to update tips
     * @param entity The tip object containing the data
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Tip entity) {
        try {
            LOGGER.log(Level.INFO, "Updating tip {0}", entity.getTip_id());
            ejb.updateTip(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());  
        }
    }

    /**
     * DELETE method to remove tips
     * @param id The id of the tip
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Removing tip {0}", id);
            ejb.removeTip(ejb.findTipById(id));
        } catch (ReadException | DeleteException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());  
        }
    }

    /**
     * GET method to get a tip 
     * @param id The id of the tip
     * @return A tip object
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Tip find(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Finding tip by id");
            return ejb.findTipById(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());  
        }
    }

    /**
     * GET method to get all the tips
     * @return A list of Tip objects
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tip> findAll() {
        try {
            LOGGER.log(Level.INFO, "Finding all tips");
            return ejb.findAllTip();
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());  
        }
    }

    /**
     * GET method to get all the tips of one type
     * @param tipType the type
     * @return A list of Tip objects
     */
    @GET
    @Path("/type/{type}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tip> findByType(@PathParam("type") TipTypeEnum tipType){
        try {
            LOGGER.log(Level.INFO, "Finding find tips by type");
            return ejb.findTipByType(tipType);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());  
        }
    }
    
}
