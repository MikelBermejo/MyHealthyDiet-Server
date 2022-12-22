/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import ejb.ClientDietInterface;
import entities.ClientDiet;
import entities.GenreEnum;
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
@Path("clientdiet")
public class ClientDietFacadeREST {

    /**
     * EJB object implementing business logic.
     */
    @EJB
    private ClientDietInterface ejb;
    

    @POST
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void create(ClientDiet clientDiet) {
        try {
            ejb.insertClientDiet(clientDiet);
        } catch (Exception e) {
        }
    }

    @PUT
    @Path("editById/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(ClientDiet clientDiet) {
        ejb.updateClientDiet(clientDiet);
    }

    @DELETE
    @Path("removeById/{id}")
    public void remove(ClientDiet clientDiet) {
        try {
            ejb.removeClientDiet(clientDiet);
        } catch (Exception e) {
        }
    }

    @GET
    @Path("findClientDietById/{client_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ClientDiet> find(@PathParam("client_id") Integer client_id) {
        return ejb.findClientDietById(client_id);
    }
    
    
    @GET
    @Path("findClientDietsForYou/{genre}/{height}/{weight}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<ClientDiet> findAll(@PathParam("genre") GenreEnum genre,@PathParam("height") Float height,@PathParam("weight") Float weight) {
        return ejb.findClientDietsForYou(genre,height,weight);
    }

    
    @GET
    @Path("findAll")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<ClientDiet> findAll() {
        return ejb.findAllClientDiets();
    }
}
