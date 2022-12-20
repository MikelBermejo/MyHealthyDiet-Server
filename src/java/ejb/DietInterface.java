package ejb;

import entities.Diet;
import entities.GoalEnum;
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
     */
    public void createDiet (Diet diet);
    
    
    /**
     * This method updates a diet data in the DB.
     * @param diet The diet entity that contains the modified the data.
     */
    public void updateDiet (Diet diet);
    
    
    /**
     * This method removes a diet from the DB.
     * @param diet The diet entity that contains the data.
     */
    public void removeDiet (Diet diet);
    
    
    /**
     * This method gets a list with all diets in the DB. 
     * @return A list of diets containing diet data.
     */
    public List<Diet> findAllDiets ();
    
    
    /**
     * This method obtains a diet from the DB using its id.
     * @param id A id used for the JPQL query
     * @return A diet entity containing diet data.
     */
    public Diet findDietById (Integer id);
    
    
    /**
     * This method obtains a list of diets from the DB using its name.
     * @param text A text used for the JPQL query
     * @return A list of diets containing diet data.
     */
    public List<Diet> findDietByName (String text);
    
    
    /**
     * This method obtains a list of diets from the DB using its goal.
     * @param goal A goal used for the JPQL query
     * @return A list of diets containing diet data.
     */
    public List<Diet> findDietByGoal (GoalEnum goal);
    
}
