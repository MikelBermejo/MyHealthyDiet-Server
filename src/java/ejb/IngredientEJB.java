/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Ingredient;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Ejb of the entity ingredient that implements its own interface named IngredientInterface
 * @author Mikel
 */
@Stateless
public class IngredientEJB implements IngredientInterface{

    @PersistenceContext(unitName = "MyHealthyDietPU")
    private EntityManager em;

    
    /**
     * 
     * @param entity 
     */
    public void create(Ingredient entity) {
        em.persist(entity);
    }

    /**
     * 
     * @param entity 
     */
    public void edit(Ingredient entity) {
        em.merge(entity);
    }

    /**
     * 
     * @param entity 
     */
    public void remove(Ingredient entity) {
        em.remove(em.merge(entity));
    }

    /**
     * 
     * @param id
     * @return 
     */
    public Ingredient find(Object id) {
        return em.find(Ingredient.class, id);
    }

    /**
     * 
     * @return 
     */
    public List<Ingredient> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Ingredient.class));
        return em.createQuery(cq).getResultList();
    }

    /**
     * 
     * @param range
     * @return 
     */
    public List<Ingredient> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Ingredient.class));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    /**
     * 
     * @return 
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Ingredient> rt = cq.from(Ingredient.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    
}
