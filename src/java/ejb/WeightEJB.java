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
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Weight.class));
        return em.createQuery(cq).getResultList();
    }

    /**
     * 
     * @param range
     * @return 
     */
    public List<Weight> findRangeOfWeight(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Weight.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * 
     * @return 
     */
    public int countWeights() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Weight> rt = cq.from(Weight.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
