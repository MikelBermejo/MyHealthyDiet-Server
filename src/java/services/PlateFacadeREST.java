package services;

import ejb.PlateInterface;
import entities.MealEnum;
import entities.Plate;
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
 * @author HaizeaF
 */
@Path("entities.plate")
public class PlateFacadeREST {
    
    @EJB
    private PlateInterface ejb;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Plate entity) {
        ejb.createPlate(entity);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Plate entity) {
        ejb.updatePlate(entity);
    }

    @DELETE
    @Path("delete/{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            ejb.removePlate(ejb.findPlate(id));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Plate find(@PathParam("id") Integer id) {
        return ejb.findPlate(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Plate> findAll() {
        return ejb.findAllPlates();
    }
    
    @GET
    @Path("findByName/{plateName}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Plate> findPlatesByName(@PathParam("plateName") String name) {
        return ejb.findPlatesByName(name);
    }
    
    @GET
    @Path("findByIngredient/{ingredients.ingredient_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Plate> findPlatesByIngredient(@PathParam("ingredients.ingredient_id") Integer ingredient_id) {
        return ejb.findPlatesByIngredient(ingredient_id);
    }
    
    @GET
    @Path("findByMealType/{mealType}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Plate> findPlatesByIngredient(@PathParam("mealType") MealEnum mealType) {
        return ejb.findPlatesByMealType(mealType);
    }
    
    @GET
    @Path("findVegetarians")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Plate> findPlatesIfVegetarian() {
        return ejb.findPlatesIfVegetarian();
    }
}
