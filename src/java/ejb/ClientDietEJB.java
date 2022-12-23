package ejb;

import entities.ClientDiet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is the stateless EJB that implements the DietInterface interface. 
 * @author JulenB
 */
@Stateless
public class ClientDietEJB implements ClientDietInterface {

    @PersistenceContext(unitName = "MyHealthyDietPU")
    private EntityManager em;
    
    @Override
    public void insertClientDiet(ClientDiet clientDiet) {
        try {
            em.persist(clientDiet);
        } catch (Exception e) {
        }
    }
    
    
    @Override
    public void updateClientDiet(ClientDiet clientDiet) {
        try {
            if (!em.contains(clientDiet)) {
                em.merge(clientDiet);
            }
            em.flush();
        } catch (Exception e) {
        }
    }
    
    
    @Override
    public void removeClientDiet(List<ClientDiet> clientDiets) {
        try {
            for (int i = 0; i < clientDiets.size(); i++) {
                em.remove(em.merge(clientDiets.get(i)));
            }
        } catch (Exception e) {
        }
    }
    

    @Override
    public List<ClientDiet> findAllClientDiets(Integer client_id) {
        List<ClientDiet> diets = null;
        try{
        diets = em.createNamedQuery("findAllClientDiets").setParameter("client_id", client_id).getResultList();
        }catch(Exception e){
            
        }
        return diets;
    }

    @Override
    public List<ClientDiet> findAll_C_D() {
        List<ClientDiet> diets = null;
        try{
        diets = em.createNamedQuery("findAll_C_D").getResultList();
        }catch(Exception e){
        }
        return diets;
    }

    @Override
    public List<ClientDiet> findClientDietsRelation(Integer client_id) {
        List<ClientDiet> diets = null;
        try{
        diets = em.createNamedQuery("findClientDietsRelation").setParameter("client_id", client_id).getResultList();
        }catch(Exception e){
            
        }
        return diets;
    }
    
    
    @Override
    public ClientDiet findClientDietsRelationActive(Integer client_id) {
        ClientDiet diet = null;
        try{
        diet = (ClientDiet) em.createNamedQuery("findClientDietsRelationActive").setParameter("client_id", client_id).getSingleResult();
        }catch(Exception e){
            
        }
        return diet;
    }
}
