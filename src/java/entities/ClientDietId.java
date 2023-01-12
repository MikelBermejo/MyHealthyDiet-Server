package entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author JulenB
 * Class that constains the two ids, client and diet.
 */
@Embeddable
public class ClientDietId implements Serializable{
    //VARIABLES
        
    /**
     * Id of a client.
     */
    private Integer user_id;
    
    
    /**
     * Id of a diet.
     */
    private Integer diet_id;
    
    
    /**
     * Get the user_id .
     * @return the user_id.
     */
    public Integer getUser_id() {
        return user_id;
    }
    
    /**
     * Set the user_id.
     *
     * @param user_id the user_id to set.
     */
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    
    
    /**
     * Get the diet_id .
     * @return the diet_id.
     */
    public Integer getDiet_id() {
        return diet_id;
    }
    
    
    /**
     * Set the diet_id.
     *
     * @param diet_id the diet_id to set.
     */
    public void setDiet_id(Integer diet_id) {
        this.diet_id = diet_id;
    }
    
    
    
    
    
    
}
