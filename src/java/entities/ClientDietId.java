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
    
    public Integer client_id;
    
    public Integer diet_id;

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }

    public Integer getDiet_id() {
        return diet_id;
    }

    public void setDiet_id(Integer diet_id) {
        this.diet_id = diet_id;
    }
    
    
    
    
    
    
}
