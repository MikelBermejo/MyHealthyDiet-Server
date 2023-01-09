package services;

import ejb.DietInterface;
import entities.Diet;
import entities.GoalEnum;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
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
 */
@Path("entities.diet")
public class DietFacadeREST {

    /**
     * EJB object implementing business logic.
     */
    @EJB
    private DietInterface ejb;

    
    /**
     * POST method to create diets: uses createDiet method.
     * @param diet The Diet containing data.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void create(Diet diet) {
        try {
            ejb.createDiet(diet);
        } catch (CreateException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }

    
    /**
     * PUT method to modify diets: uses updateDiet method.
     * @param diet The Diet containing data.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void edit(Diet diet) {
        try {
            ejb.updateDiet(diet);
        } catch (UpdateException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }

    
    /**
     * DELETE method to remove diets: uses removeDiet method.
     * @param id The id for the diet to be deleted.
     */
    @DELETE
    @Path("findDietById/{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            ejb.removeDiet(ejb.findDietById(id));
        } catch (ReadException|DeleteException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }
    
    
    /**
     * GET method for getting all diets: uses findAllDiets method.
     * @return A list of diets.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Diet> findAllDiets() {
        try {
            return ejb.findAllDiets();
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }

    
    /**
     * GET method for getting a diet by its id: uses findDietById method.
     * @param id The id Diet.
     * @return A Diet object.
     */
    @GET
    @Path("findDietById/{id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Diet findById(@PathParam("id") Integer id) {
        try {
            return ejb.findDietById(id);
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }

    
    /**
     * GET method for getting a diet by its name: uses findDietByName method.
     * @param name  The name Diet.
     * @return A Diet object.
     */
    @GET
    @Path("findAllByName/{name}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Diet> findAllDietsByName(@PathParam("name") String name) {
        try {
            return ejb.findDietByName(name);
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }
    
    
    /**
     * GET method for getting a diet by its goal: uses findDietByGoal method.
     * @param goal The goal Diet.
     * @return A Diet object.
     */
    @GET
    @Path("findAllByGoal/{goal}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Diet> findAllDietsByGoal(@PathParam("goal") GoalEnum goal) {
        try {
            return ejb.findDietByGoal(goal);
        } catch (ReadException e) {
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }
}
