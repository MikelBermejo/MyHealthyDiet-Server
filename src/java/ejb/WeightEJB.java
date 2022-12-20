/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;


import entities.Weight;
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
    public void createWeight(Weight entity) {
        em.persist(entity);
    }
    
    /**
     * 
     * @param entity 
     */
    public void editWeight(Weight entity) {
        em.merge(entity);
    }
    
    /**
     * 
     * @param entity 
     */
    public void removeWeight(Weight entity) {
        em.remove(em.merge(entity));
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public Weight findWeight(Object id) {
        return em.find(Weight.class, id);
    }
    
    /**
     * 
     * @return 
     */
    public List<Weight> findAllWeights() {
        return em.createNamedQuery("findAllWeights").getResultList();
    }
}
