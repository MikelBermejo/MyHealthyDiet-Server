package ejb;

import entities.ClientDiet;
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
 *
 * @author JulenB
 */
@Stateless
public class ClientDietEJB implements ClientDietInterface {

    @PersistenceContext(unitName = "MyHealthyDietPU")
    private EntityManager em;

    /**
     * This method obtains a list of diets of a client from the DB using.
     *
     * @param clientDiet The clientDiet entity that contains the new data.
     */
    @Override
    public void insertClientDiet(ClientDiet clientDiet) throws CreateException {
        try {
            if (!em.contains(clientDiet.getDiet())) {
                clientDiet.setDiet(em.merge(clientDiet.getDiet()));
            }
            
            if (!em.contains(clientDiet.getClient())) {
                clientDiet.setClient(em.merge(clientDiet.getClient()));
            }
            
            em.persist(clientDiet);  
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * This method updates a clientDiet from the DB.
     *
     * @param clientDiet The clientDiet entity that contains the modified data.
     */
    @Override
    public void updateClientDiet(ClientDiet clientDiet) throws UpdateException {
        try {
            if (!em.contains(clientDiet)) {
                em.merge(clientDiet);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void removeClientDiet(List<ClientDiet> clientDiets) throws DeleteException {
        try {
            for (int i = 0; i < clientDiets.size(); i++) {
                em.remove(em.merge(clientDiets.get(i)));
            }
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * This method obtains a list of diets of a client from the DB.
     *
     * @param user_id The user_id of the client.
     * @return A list of clients containing client diets data.
     */
    @Override
    public List<ClientDiet> findAllClientDiets(Integer user_id) throws ReadException {
        List<ClientDiet> diets = null;
        try {
            diets = em.createNamedQuery("findAllClientDiets").setParameter("user_id", user_id).getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return diets;
    }

    /**
     * This method obtains a list of all diets and all clients from the DB.
     *
     * @return A list of all diets and all clients.
     */
    @Override
    public List<ClientDiet> findAll_C_D() throws ReadException {
        List<ClientDiet> diets = null;
        try {
            diets = em.createNamedQuery("findAll_C_D").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return diets;
    }

    /**
     * This method obtains a list of diets of a client which are not active from
     * the DB.
     *
     * @param user_id The user_id of the client.
     * @return A list of diets containing client diets data.
     */
    @Override
    public List<ClientDiet> findClientDietsRelation(Integer user_id) throws ReadException {
        List<ClientDiet> diets = null;
        try {
            diets = em.createNamedQuery("findClientDietsRelation").setParameter("user_id", user_id).getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return diets;
    }

    /**
     * This method obtains a list of diets of a client which are active from the
     * DB.
     *
     * @param user_id The user_id of the client.
     * @return A list of diets containing client diets data.
     */
    @Override
    public List<ClientDiet> findClientDietRelationIsActive(Integer user_id) throws ReadException {
        List<ClientDiet> diets = null;
        try {
            diets = em.createNamedQuery("findClientDietRelationIsActive").setParameter("user_id", user_id).getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return diets;
    }
}
