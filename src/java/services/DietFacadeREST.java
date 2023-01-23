package services;

import java.util.logging.Logger;
import ejb.DietInterface;
import entities.Diet;
import entities.GoalEnum;
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
 * RESTful Service of the diets.
 */
@Path("diet")
public class DietFacadeREST {

    /**
     * EJB object implementing business logic.
     */
    @EJB
    private DietInterface ejb;
    
    private static final Logger LOGGER=Logger.getLogger(TipFacadeREST.class.getName());

    
    /**
     * POST method to create diets: uses createDiet method.
     * @param diet The Diet containing data.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void create(Diet diet) {
        try {
            LOGGER.log(Level.INFO, "Creating diet {0}", diet.getDiet_id());
            diet.setDiet_id(null);
            ejb.createDiet(diet);
        } catch (CreateException e) {
            LOGGER.severe(e.getMessage());
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
            LOGGER.log(Level.INFO, "Updating diet {0}", diet.getDiet_id());
            ejb.updateDiet(diet);
        } catch (UpdateException e) {
            LOGGER.severe(e.getMessage());
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
            LOGGER.log(Level.INFO, "Removing diet {0}", id);
            ejb.removeDiet(ejb.findDietById(id));
        } catch (ReadException|DeleteException e) {
            LOGGER.severe(e.getMessage());
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
            LOGGER.log(Level.INFO, "Finding all diets");
            return ejb.findAllDiets();
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
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
            LOGGER.log(Level.INFO, "Finding client by id");
            return ejb.findDietById(id);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
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
            LOGGER.log(Level.INFO, "Finding client by name");
            return ejb.findDietByName(name);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
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
            
            LOGGER.log(Level.INFO, "Finding client by goal");
            return ejb.findDietByGoal(goal);
        } catch (ReadException e) {
            LOGGER.severe(e.getMessage());
            throw new InternalServerErrorException(e.getMessage()); 
        }
    }
}
