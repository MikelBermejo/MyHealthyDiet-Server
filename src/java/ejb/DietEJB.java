package ejb;

import entities.Diet;
import entities.GoalEnum;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is the stateless EJB that implements the DietInterface interface. 
 * @author JulenB
 */
@Stateless
public class DietEJB implements DietInterface {

    @PersistenceContext(unitName = "MyHealthyDietPU")
    private EntityManager em;

    
    /**
     * This method creates a new diet in the DB.
     * @param diet The Diet entity that contains the new data.
     */
    @Override
    public void createDiet(Diet diet) {
        try {
            em.persist(diet);
        } catch (Exception e) {
        }
    }

    
    /**
     * This method updates an diet data in the DB.
     * @param diet The Diet entity that contains the modified data.
     */
    @Override
    public void updateDiet(Diet diet) {
        try {
            if (!em.contains(diet)) {
                em.merge(diet);
            }
            em.flush();
        } catch (Exception e) {
        }
    }

    
    /**
     * This method removes a diet from the DB.
     * @param diet The Diet entity that contains the data to remove.
     */
    @Override
    public void removeDiet(Diet diet) {
        try {
            em.remove(em.merge(diet));
        } catch (Exception e) {
        }
    }
    
    
    /**
     * This method gets a list with all diets in the DB. 
     * @return A list of diets containing diet data.
     */
    @Override
    public List<Diet> findAllDiets() {
        List<Diet> diets = null;
        try{
        diets = em.createNamedQuery("findAllDiets").getResultList();
        }catch(Exception e){
            
        }
        return diets;
    }
    
    
    /**
     * This method obtains a diet from the DB using its name.
     * @param id A id used for the JPQL query
     * @return A diet entity containing diet data.
     */
    @Override
    public Diet findDietById(Integer id) {
        Diet diet = null;
        try{
        diet = em.find(Diet.class, id);
        }catch(Exception e){
            
        }
        return diet;
    }

    
    /**
     * This method obtains a list of diets from the DB using its name.
     * @param text A text used for the JPQL query
     * @return A list of diets containing diet data.
     */
    @Override
    public List<Diet> findDietByName(String text) {
        List<Diet> diets = null;
        try{
        diets = em.createNamedQuery("findDietByName").setParameter("text", "%" + text + "%").getResultList();
        }catch(Exception e){
            
        }
        return diets;
    }

    
    /**
     * This method obtains a list of diets from the DB using its goal.
     * @param goal A goal used for the JPQL query
     * @return A list of diets containing diet data.
     */
    @Override
    public List<Diet> findDietByGoal(GoalEnum goal) {
        List<Diet> diets = null;
        try{
        diets = em.createNamedQuery("findDietByGoal").setParameter("goal", goal).getResultList();
        }catch(Exception e){
            
        }
        return diets;
    }
}
