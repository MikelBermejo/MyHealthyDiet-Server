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
    
    private Integer user_id;
    
    private Integer diet_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getDiet_id() {
        return diet_id;
    }

    public void setDiet_id(Integer diet_id) {
        this.diet_id = diet_id;
    }
    
    
    
    
    
    
}
