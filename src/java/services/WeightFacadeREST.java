/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import ejb.WeightEJB;
import ejb.WeightInterface;
import entities.Weight;
import java.util.List;
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
 * @author Mikel
 */
@Path("entities.weight")
public class WeightFacadeREST {

    @EJB
    private WeightInterface ejb;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createWeight(Weight entity) {
        ejb.createWeight(entity);
    }

    @PUT
    @Path("{entity}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void editWeight(Weight entity) {
        ejb.editWeight(entity);
    }

    @DELETE
    @Path("{id}")
    public void removeWeight(@PathParam("id") Integer id) {
        ejb.removeWeight(ejb.findWeight(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Weight findWeight(@PathParam("id") Integer id) {
        return ejb.findWeight(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Weight> findAllWeights() {
        return ejb.findAllWeights();
    }
    
}
