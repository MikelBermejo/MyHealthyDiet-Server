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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
@Path("entities.tip")
public class TipFacadeREST {

    /**
     * EJB object implementing business logic.
     */
    @EJB
    private TipInterface ejb;

    private Logger LOGGER=Logger.getLogger(TipFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Tip entity) {
        LOGGER.log(Level.INFO, "Creating tip {0}", entity.getTip_id());
        ejb.createTip(entity);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Tip entity) {
        LOGGER.log(Level.INFO, "Updating tip {0}", entity.getTip_id());
        ejb.updateTip(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        LOGGER.log(Level.INFO, "Removing tip {0}", id);
        ejb.removeTip(ejb.findTipById(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Tip find(@PathParam("id") Integer id) {
        LOGGER.log(Level.INFO, "Finding tip by id");
        return ejb.findTipById(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tip> findAll() {
        LOGGER.log(Level.INFO, "Finding all tips");
        return ejb.findAllTip();
    }

    @GET
    @Path("/type/{type}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tip> findByType(@PathParam("type") TipTypeEnum tipType){
        LOGGER.log(Level.INFO, "Finding find tips by type");
        return ejb.findTipByType(tipType);
    }
    
}
