package ejb;

import entities.ClientDiet;
import entities.Client;
import entities.GenreEnum;
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
    public List<ClientDiet> findClientDietsById(Integer client_id) {
        List<ClientDiet> clientDiets = null;
        try {
            clientDiets = em.createNamedQuery("findAllCientDiets").setParameter("id", client_id).getResultList();
        } catch (Exception e) {
        }
        return clientDiets;   
    }
    

    @Override
    public List<ClientDiet> findClientDietsForYou(GenreEnum genre,Float height,Float weight) {
        List<ClientDiet> diets = null;
        try {
            diets = em.createNamedQuery("findClientDietsForYou").setParameter("genre", genre).setParameter("height", height).setParameter("weight", weight).getResultList();
        } catch (Exception e) {
        }
        return diets;
        
    }
    

    @Override
    public List<ClientDiet> findAllClientDiets() {
        List<ClientDiet> diets = null;
        try{
        diets = em.createNamedQuery("findAll").getResultList();
        }catch(Exception e){
            
        }
        return diets;
    }
}
