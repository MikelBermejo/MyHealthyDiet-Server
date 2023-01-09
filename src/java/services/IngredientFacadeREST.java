/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import ejb.IngredientEJB;
import ejb.IngredientInterface;
import entities.Ingredient;
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
@Path("entities.ingredient")
public class IngredientFacadeREST {

    
    @EJB
    private IngredientInterface ejb;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Ingredient entity) {
        try{
            ejb.createIngredient(entity);
        } catch (CreateException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Ingredient entity) {
        try {
            ejb.editIngredient(entity);
        } catch (UpdateException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }

    @DELETE
    @Path("/ingredient/{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            ejb.removeIngredient(ejb.findIngredient(id));  
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        } catch (DeleteException ex) {
            throw new InternalServerErrorException(ex.getMessage()); 
        }
    }

    @GET
    @Path("/ingredient/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Ingredient find(@PathParam("id") Integer id) {
        try {
            return ejb.findIngredient(id);
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Ingredient> findAll() {
        try {
            return ejb.findAllIngredients();
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }
    
    @GET
    @Path("{ingredientName}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Ingredient> findIngredientsByName(@PathParam("ingredientName") String ingredientName) {
        try {    
            return ejb.findIngredientsByName(ingredientName);
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }
}
