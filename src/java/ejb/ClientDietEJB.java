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
    public void removeClientDiet(ClientDiet clientDiet) {
        try {
            em.remove(em.merge(clientDiet));
        } catch (Exception e) {
        }
    }
    
    
    @Override
    public ClientDiet findClientDietById(Integer id) {
        ClientDiet clientDiet = null;
        try {
            clientDiet = em.find(ClientDiet.class, id);
        } catch (Exception e) {
        }
        return clientDiet;   
    }
    

    @Override
    public List<ClientDiet> findClientDietsForYou(Integer client_user_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public List<ClientDiet> findAllClientDiets() {
        List<ClientDiet> diets = null;
        try{
        diets = em.createNamedQuery("findAllClientDiets").getResultList();
        }catch(Exception e){
            
        }
        return diets;
    }
}
