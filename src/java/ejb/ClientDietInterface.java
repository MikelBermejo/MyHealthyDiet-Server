package ejb;

import entities.ClientDiet;
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
public interface ClientDietInterface {
    
    
    /**
     * This method obtains a list of diets of a client from the DB using.
     * @param clientDiet The clientDiet entity that contains the new data.
     * @throws exceptions.CreateException
     */
    public void insertClientDiet(ClientDiet clientDiet) throws CreateException;
    
    
    /**
     * This method updates a clientDiet from the DB.
     * @param clientDiet The clientDiet entity that contains the modified data.
     * @throws exceptions.UpdateException
     */
    public void updateClientDiet (ClientDiet clientDiet) throws UpdateException;
    
    
    /**REVISAR
     * This method removes a clientDiet from the DB.
     * @param clientDiets The list 
     * @throws exceptions.DeleteException 
     */
    public void removeClientDiet (List<ClientDiet> clientDiets) throws DeleteException;
    
    
    /**
     * This method obtains a list of diets of a client from the DB.
     * @param client_id The client_id of the client.
     * @return A list of clients containing client diets data.
     * @throws exceptions.ReadException
     */
    public List<ClientDiet> findAllClientDiets (Integer client_id) throws ReadException;
    
    
    /**
     * This method obtains a list of all diets and all clients from the DB.
     * @return A list of all diets and all clients.
     * @throws exceptions.ReadException
     */
    public List<ClientDiet> findAll_C_D () throws ReadException;
    
    
    /**
     * This method obtains a list of diets of a client which are not active from the DB.
     * @param client_id The client_id of the client.
     * @return A list of diets containing client diets data.
     * @throws exceptions.ReadException
     */
    public List<ClientDiet> findClientDietsRelation (Integer client_id) throws ReadException;
    
    
    /**
     * This method obtains a list of diets of a client which are active from the DB.
     * @param client_id The client_id of the client.
     * @return A list of diets containing client diets data.
     * @throws exceptions.ReadException
     */
    public List<ClientDiet> findClientDietRelationIsActive (Integer client_id) throws ReadException;
       
}
