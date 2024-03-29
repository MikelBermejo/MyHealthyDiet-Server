/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;


import entities.Weight;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Ejb of the entity weight that implements his own interface WeightInterface
 * @author Mikel
 */
@Stateless
public class WeightEJB implements WeightInterface{

    @PersistenceContext(unitName = "MyHealthyDietPU")
    private EntityManager em;

    /**
     * Method that creates a weight.
     * @param entity 
     */
    public void createWeight(Weight entity) throws CreateException {
        try {
            em.persist(entity);
        }catch(Exception e){
            throw new CreateException(e.getMessage());
        }
    }
    
    /**
     * Method to edit a weight.
     * @param entity 
     */
    public void editWeight(Weight entity) throws UpdateException {
        try{
            if (!em.contains(entity)) {
                   em.merge(entity);
                }
                em.flush();
        }catch(Exception e){
            throw new UpdateException(e.getMessage());
        }
    }
    
    /**
     * Method to remove a weight.
     * @param entity 
     */
    public void removeWeight(Weight entity) throws DeleteException {
        try{
            em.remove(em.merge(entity));   
        }catch(Exception e){
            throw new DeleteException(e.getMessage());
        }    
    }
    
    /**
     * Method to search a weight by id.
     * @param id
     * @return 
     */
    public Weight findWeight(Object id) throws ReadException{
        try{
            return em.find(Weight.class, id);
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        }  
    }
    
    /**
     * Method to show all the weights.
     * @return 
     */
    public List<Weight> findAllWeights() throws ReadException {
        try{
            return em.createNamedQuery("findAllWeights").getResultList();
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        } 
    }
    
    /**
     * Method that shows all the weight of a client.
     * @param id
     * @return
     * @throws ReadException 
     */
    @Override
    public List<Weight> findAllWeightsByClient(Object id) throws ReadException {
        try{
            return em.createNamedQuery("findAllWeightsByClient").setParameter("id", "%"+id+"%").getResultList();
        }catch (Exception e){
            throw new ReadException(e.getMessage());
        }
    }
}
