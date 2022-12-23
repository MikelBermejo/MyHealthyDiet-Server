/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import ejb.ClientDietEJB;
import ejb.ClientDietInterface;
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
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(ClientDiet clientDiet) {
        ejb.updateClientDiet(clientDiet);
    }

    @DELETE
    @Path("findClientDietsRelation/{client_id}")
    public void remove(@PathParam("client_id") Integer client_id) {
        try {
            ejb.removeClientDiet(ejb.findClientDietsRelation(client_id));
        } catch (Exception e) {
        }
    }

    @GET
    @Path("findAllClientDiets/{client_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ClientDiet> find(@PathParam("client_id") Integer client_id) {
        return ejb.findAllClientDiets(client_id);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ClientDiet> findAll() {
        return ejb.findAll_C_D();
    }
    
    
    @GET
    @Path("findClientDietsRelation/{client_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ClientDiet> findClientDietsRelation(@PathParam("client_id") Integer client_id) {
        return ejb.findClientDietsRelation(client_id);
    }
}
