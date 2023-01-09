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
     * 
     * @param entity 
     */
    public void createWeight(Weight entity) throws CreateException {
        em.persist(entity);
    }
    
    /**
     * 
     * @param entity 
     */
    public void editWeight(Weight entity) throws UpdateException {
        if (!em.contains(entity)) {
                em.merge(entity);
            }
            em.flush();
    }
    
    /**
     * 
     * @param entity 
     */
    public void removeWeight(Weight entity) throws DeleteException {
        em.remove(em.merge(entity));
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public Weight findWeight(Object id) throws ReadException{
        return em.find(Weight.class, id);
    }
    
    /**
     * 
     * @return 
     */
    public List<Weight> findAllWeights() throws ReadException {
        return em.createNamedQuery("findAllWeights").getResultList();
    }
}
