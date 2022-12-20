/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import ejb.IngredientEJB;
import ejb.IngredientInterface;
import entities.Ingredient;
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
@Path("entities.ingredient")
public class IngredientFacadeREST {

    
    @EJB
    private IngredientInterface ejb;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Ingredient entity) {
        ejb.createIngredient(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Ingredient entity) {
        ejb.editIngredient(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        ejb.removeIngredient(ejb.findIngredient(id));
    }

    @GET
    @Path("/ingredient/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Ingredient find(@PathParam("id") Integer id) {
        return ejb.findIngredient(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Ingredient> findAll() {
        return ejb.findAllIngredients();
    }
    
    @GET
    @Path("{ingredientName}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Ingredient> findIngredientsByName(@PathParam("ingredientName") String ingredientName) {
        return ejb.findIngredientsByName(ingredientName);    
    }
}
