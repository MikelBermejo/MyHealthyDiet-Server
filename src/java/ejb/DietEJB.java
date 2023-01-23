package ejb;

import entities.Diet;
import entities.GoalEnum;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
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
     * @throws CreateException Thrown when an exception occurs while creating a diet.
     */
    @Override
    public void createDiet(Diet diet) throws CreateException{
        try {
            em.persist(diet);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    
    /**
     * This method updates an diet data in the DB.
     * @param diet The Diet entity that contains the modified data.
     * @throws UpdateException Thrown when an exception occurs while updating a diet.
     */
    @Override
    public void updateDiet(Diet diet) throws UpdateException{
        try {
            if (!em.contains(diet)) {
                em.merge(diet);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    
    /**
     * This method removes a diet from the DB.
     * @param diet The Diet entity that contains the data to remove.
     * @throws DeleteException Thrown when an exception occurs while removing a diet.
     */
    @Override
    public void removeDiet(Diet diet) throws DeleteException{
        try {
            em.remove(em.merge(diet));
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }
    
    
    /**
     * This method gets a list with all diets in the DB. 
     * @return A list of diets containing diet data.
     * @throws ReadException Thrown when an exception occurs while reading a diet.
     */
    @Override
    public List<Diet> findAllDiets() throws ReadException{
        List<Diet> diets = null;
        try{
        diets = em.createNamedQuery("findAllDiets").getResultList();
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        }
        return diets;
    }
    
    
    /**
     * This method obtains a diet from the DB using its name.
     * @param id A id used for the JPQL query
     * @return A diet entity containing diet data.
     * @throws ReadException Thrown when an exception occurs while reading a diet.
     */
    @Override
    public Diet findDietById(Integer id) throws ReadException{
        Diet diet = null;
        try{
        diet = em.find(Diet.class, id);
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        }
        return diet;
    }

    
    /**
     * This method obtains a list of diets from the DB using its name.
     * @param text A text used for the JPQL query
     * @return A list of diets containing diet data.
     * @throws ReadException Thrown when an exception occurs while reading a diet.
     */
    @Override
    public List<Diet> findDietByName(String text) throws ReadException {
        List<Diet> diets = null;
        try{
        diets = em.createNamedQuery("findDietByName").setParameter("text", "%" + text + "%").getResultList();
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        }
        return diets;
    }

    
    /**
     * This method obtains a list of diets from the DB using its goal.
     * @param goal A goal used for the JPQL query
     * @return A list of diets containing diet data.
     * @throws ReadException Thrown when an exception occurs while reading a diet.
     */
    @Override
    public List<Diet> findDietByGoal(GoalEnum goal) throws ReadException {
        List<Diet> diets = null;
        try{
        diets = em.createNamedQuery("findDietByGoal").setParameter("goal", goal).getResultList();
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        }
        return diets;
    }
}
