package ejb;

import entities.ClientDiet;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author JulenB
 */
@Local
public interface ClientDietInterface {
    
    
    /**
     * This method obtains a list of diets of a client from the DB using.
     * @param clientDiet The clientDiet entity that contains the new data.
     */
    public void insertClientDiet(ClientDiet clientDiet);
    
    
    /**
     * This method updates a clientDiet from the DB.
     * @param clientDiet The clientDiet entity that contains the modified data.
     */
    public void updateClientDiet (ClientDiet clientDiet);
    
    
    /**
     * This method removes a clientDiet from the DB.
     * @param clientDiet The clientDiet entity that contains the data that is going to remove.
     */
    public void removeClientDiet (ClientDiet clientDiet);
    
    
    /**
     * This method obtains a diet from the DB using its id.
     * @param id A id used for the JPQL query
     * @return A diet entity containing diet data.
     */
    public ClientDiet findClientDietById (Integer id);
    
    
    /**
     * This method obtains a list of diets of a client from the DB using.
     * @return A list of clients containing client diets data.
     */
    public List<ClientDiet> findClientDietsForYou (Integer client_user_id);
    
    
    /**
     * This method obtains a list of diets of a client from the DB.
     * @return A list of clients containing client diets data.
     */
    public List<ClientDiet> findAllClientDiets ();
    
}
