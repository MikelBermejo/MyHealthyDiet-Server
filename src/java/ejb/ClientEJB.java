/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Client;
import entities.StatusEnum;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 2dam
 */
@Stateless
public class ClientEJB implements ClientInterface{

    /**
     * EntityManager for MyHealthyDietPU persistence unit.
     */
    @PersistenceContext(unitName = "MyHealthyDietPU")
    private EntityManager em;
    
    @Override
    public void createClient(Client client) {
        em.persist(client);
    }

    @Override
    public void updateClient(Client client) {
        if(!em.contains(client))
            em.merge(client);
        em.flush();
    }

    @Override
    public void removeClient(Client client) {
        em.remove(em.merge(client));
    }

    @Override
    public List<Client> findAllClient() {
        List<Client> clients;
        
        clients= em.createNamedQuery("findAllClient").getResultList();
        
        return clients;
    }

    @Override
    public Client findClientById(Integer id) {
        Client client;
        
        client=em.find(Client.class, id);
        
        return client;
    }

    @Override
    public List<Client> findClientBySearch(String value) {
        List<Client> clients;
        
        clients= em.createNamedQuery("findClientBySearch").setParameter("usrValue", value).getResultList();
        
        return clients;
    }

    @Override
    public List<Client> findClientByStatus(StatusEnum status) {
        List<Client> clients;
        
        clients= em.createNamedQuery("findClientByStatus").setParameter("usrStatus", status).getResultList();
        
        return clients;
    }
    
}
