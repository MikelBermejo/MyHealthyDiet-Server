/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import ejb.ClientDietEJB;
import entities.ClientDiet;
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
    private ClientDietEJB ejb;
    

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
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            ejb.removeClientDiet(ejb.findClientDietById(id));
        } catch (Exception e) {
        }
    }

    @GET
    @Path("findClientDietById/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ClientDiet find(@PathParam("id") Integer id) {
        return ejb.findClientDietById(id);
    }
    
    
    @GET
    @Path("findClientDietsForYou/{client_user_id}")
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<ClientDiet> findAll(@PathParam("client_user_id") Integer client_user_id) {
        return ejb.findClientDietsForYou(client_user_id);
    }

    
    @GET
    @Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public List<ClientDiet> findAll() {
        return ejb.findAllClientDiets();
    }
}
