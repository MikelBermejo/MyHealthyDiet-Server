package services;

import ejb.PlateInterface;
import entities.MealEnum;
import entities.Plate;
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
 * @author HaizeaF
 * RESTful Service of the plates
 */
@Path("plate")
public class PlateFacadeREST {
    
    /**
     * EJB that implements the PlateInterface
     */
    @EJB
    private PlateInterface ejb;
    
    private static final Logger LOGGER=Logger.getLogger(PlateFacadeREST.class.getName());
    
    /**
     * Post method that creates a new plate
     * @param entity The plate we want to create
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Plate entity) {
        try {
            LOGGER.log(Level.INFO,"Creating plate {0}", entity.getPlate_id());
            ejb.createPlate(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    /**
     * Put method that updates the data of a plate
     * @param entity The plate we want to update
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Plate entity) {
        try {
            LOGGER.log(Level.INFO,"Updating plate {0}", entity.getPlate_id());
            ejb.updatePlate(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    /**
     * Delete method that deletes a plate
     * @param id The id of the plate we want to delete
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO,"Removing plate {0}", id);
            ejb.removePlate(ejb.findPlate(id));
        } catch (DeleteException | ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    /**
     * Get method that obtains a plate by id
     * @param id The id of the plate we want to find
     * @return The plate we want to obtain
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Plate find(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO,"Obtaining plate {0}", id);
            return ejb.findPlate(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    /**
     * Get method that obtains all the plates
     * @return All the plates
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Plate> findAll() {
        try {
            LOGGER.log(Level.INFO,"Obtaining all plates");
            return ejb.findAllPlates();
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    /**
     * Get method that obtains the plates whose names contain specific characters
     * @param name The characters that must contain the names of the plates that we want to find
     * @return The plates we want to obtain
     */
    @GET
    @Path("findByName/{plateName}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Plate> findPlatesByName(@PathParam("plateName") String name) {
        try {
            LOGGER.log(Level.INFO,"Obtaining plates by name: {0}", name);
            return ejb.findPlatesByName(name);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    /**
     * Get method that obtains plates by ingredient
     * @param ingredient_id The id of the ingredient that the plates we want to find contain
     * @return The plates we want to obtain
     */
    @GET
    @Path("findByIngredient/{ingredients.ingredient_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Plate> findPlatesByIngredient(@PathParam("ingredients.ingredient_id") Integer ingredient_id) {
        try {
            LOGGER.log(Level.INFO,"Obtaining plates by ingredient id: {0}", ingredient_id);
            return ejb.findPlatesByIngredient(ingredient_id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    /**
     * Get method that obtains the plates depending on the time of day they belong to (by mealType)
     * @param mealType The meal type of the plates that we want to find
     * @return The plates we want to obtain
     */
    @GET
    @Path("findByMealType/{mealType}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Plate> findPlatesByMealType(@PathParam("mealType") MealEnum mealType) {
        try {
            LOGGER.log(Level.INFO,"Obtaining plates by meal type: {0}", mealType);
            return ejb.findPlatesByMealType(mealType);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    /**
     * Get method that obtains all the vegetarian plates
     * @return All the vegetarian plates
     */
    @GET
    @Path("findVegetarians")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Plate> findPlatesIfVegetarian() {
        try {
            LOGGER.log(Level.INFO,"Obtaining plates that are vegetarian");
            return ejb.findPlatesIfVegetarian();
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
}
