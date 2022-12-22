package services;

import ejb.DietInterface;
import entities.Diet;
import entities.GoalEnum;
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
 * @author JulenB
 */
@Path("entities.diet")
public class DietFacadeREST {

    /**
     * EJB object implementing business logic.
     */
    @EJB
    private DietInterface ejb;

    @POST
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void create(Diet diet) {
        try {
            ejb.createDiet(diet);
        } catch (Exception e) {
        }
    }

    @PUT
    @Path("editById/{id}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void edit(Diet entity) {
        ejb.updateDiet(entity);
    }

    @DELETE
    @Path("removeById/{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            ejb.removeDiet(ejb.findDietById(id));
        } catch (Exception e) {
        }
    }

    @GET
    @Path("findDietById/{id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public Diet find(@PathParam("id") Integer id) {
        return ejb.findDietById(id);
    }

    @GET
    @Path("findAllByName/{name}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Diet> findAll(@PathParam("name") String name) {
        return ejb.findDietByName(name);
    }
    
    @GET
    @Path("findAllByGoal/{goal}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<Diet> findAll(@PathParam("goal") GoalEnum goal) {
        return ejb.findDietByGoal(goal);
    }
}
