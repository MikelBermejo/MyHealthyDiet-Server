package ejb;

import entities.Diet;
import entities.GoalEnum;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author JulenB
 */
@Local
public interface DietInterface {
    
    /**
     * This method creates a new diet in the DB.
     * @param diet The diet entity that contains the new data.
     * @throws exceptions.CreateException
     */
    public void createDiet (Diet diet) throws CreateException;
    
    
    /**
     * This method updates a diet data in the DB.
     * @param diet The diet entity that contains the modified the data.
     * @throws exceptions.UpdateException
     */
    public void updateDiet (Diet diet) throws UpdateException;
    
    
    /**
     * This method removes a diet from the DB.
     * @param diet The diet entity that contains the data.
     * @throws exceptions.DeleteException
     */
    public void removeDiet (Diet diet) throws DeleteException;
    
    
    /**
     * This method gets a list with all diets in the DB. 
     * @return A list of diets containing diet data.
     * @throws exceptions.ReadException
     */
    public List<Diet> findAllDiets () throws ReadException;
    
    
    /**
     * This method obtains a diet from the DB using its id.
     * @param id A id used for the JPQL query
     * @return A diet entity containing diet data.
     * @throws exceptions.ReadException
     */
    public Diet findDietById (Integer id) throws ReadException;
    
    
    /**
     * This method obtains a list of diets from the DB using its name.
     * @param text A text used for the JPQL query
     * @return A list of diets containing diet data.
     * @throws exceptions.ReadException
     */
    public List<Diet> findDietByName (String text) throws ReadException;
    
    
    /**
     * This method obtains a list of diets from the DB using its goal.
     * @param goal A goal used for the JPQL query
     * @return A list of diets containing diet data.
     * @throws exceptions.ReadException
     */
    public List<Diet> findDietByGoal (GoalEnum goal) throws ReadException;
    
}
