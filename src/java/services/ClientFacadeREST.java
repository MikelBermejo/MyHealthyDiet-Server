/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import ejb.ClientInterface;
import ejb.TipInterface;
import entities.Client;
import entities.StatusEnum;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
 * @author Sendoa
 */
@Path("entities.client")
public class ClientFacadeREST {

    /**
     * EJB object implementing business logic.
     */
    @EJB
    private ClientInterface ejb;

    private Logger LOGGER=Logger.getLogger(TipFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Client entity) {
        ejb.createClient(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Client entity) {
        ejb.updateClient(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(Client entity) {
        ejb.removeClient(entity);
    }

    @GET
    @Path("/client/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Client findClientById(@PathParam("id") Integer id) {
        return ejb.findClientById(id);
    }
    
    @GET
    @Path("{status}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Client> findClientByStatus(@PathParam("status") StatusEnum status) {
        return ejb.findClientByStatus(status);
    }
    
    @GET
    @Path("/search/{usrValue}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Client> findClientBySearch(@PathParam("usrValue") String usrValue) {
        return ejb.findClientBySearch(usrValue);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Client> findAll() {
        return ejb.findAllClient();
    }
    
    
    
}
