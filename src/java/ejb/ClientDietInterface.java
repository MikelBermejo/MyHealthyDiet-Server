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
    public void updateClientDiet (ClientDiet clientDiets);
    
    
    /**
     * This method removes a clientDiet from the DB.
     */
    public void removeClientDiet (List<ClientDiet> clientDiets);
    
    
    /**
     * This method obtains a list of diets of a client from the DB.
     * @param client_id
     * @return A list of clients containing client diets data.
     */
    public List<ClientDiet> findAllClientDiets (Integer client_id);
    
    
    /**
     * This method obtains a list of diets of a client from the DB.
     * @return A list of clients containing client diets data.
     */
    public List<ClientDiet> findAll_C_D ();
    
    
    public List<ClientDiet> findClientDietsRelation (Integer client_id);
    
    
    public ClientDiet findClientDietsRelationActive (Integer client_id);
       
}
