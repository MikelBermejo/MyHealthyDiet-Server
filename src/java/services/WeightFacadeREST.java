/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import ejb.WeightEJB;
import ejb.WeightInterface;
import entities.Weight;
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
 * @author Mikel
 */
@Path("weight")
public class WeightFacadeREST {
    
    private Logger LOGGER=Logger.getLogger(WeightFacadeREST.class.getName());
    
    @EJB
    private WeightInterface ejb;
    
    /**
     * Create Restfull method.S
     * @param entity 
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createWeight(Weight entity) {
        try {
            LOGGER.log(Level.INFO, "Creating weight {0}", entity.getWeight_id());
            ejb.createWeight(entity);
        } catch (CreateException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }
    
    /**
     * Edit Restfull method.
     * @param entity 
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void editWeight(Weight entity) {
        try {
            LOGGER.log(Level.INFO, "Updating weight {0}", entity.getWeight_id());
            ejb.editWeight(entity);
        } catch (UpdateException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }
    
    /**
     * Delete Restfull method.
     * @param id 
     */
    @DELETE
    @Path("{id}")
    public void removeWeight(@PathParam("id") Integer id) {
        try {
            try {
                LOGGER.log(Level.INFO, "Removing weight {0}", id);
                ejb.removeWeight(ejb.findWeight(id));
            } catch (ReadException e) {
                throw new InternalServerErrorException(e.getMessage()); 
            }
        } catch (DeleteException ex) {
            throw new InternalServerErrorException(ex.getMessage()); 
        }
    }
    
    /**
     * Find Restfull method.
     * @param id
     * @return 
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Weight findWeight(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Finding weight {0}", id);
            return ejb.findWeight(id);
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }
    
    /**
     * FindAll Restfull method.
     * @return 
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Weight> findAllWeights() {
        try {
            LOGGER.log(Level.INFO, "Finding all weights");
            return ejb.findAllWeights();
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }
    
    /**
     * FindAllInClient Restfull method.
     * @param id
     * @return 
     */
    @GET
    @Path("(id)")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Weight> findAllWeightsByClient(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "Finding weight {0}", id);
            return ejb.findAllWeightsByClient(id);
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }
}
