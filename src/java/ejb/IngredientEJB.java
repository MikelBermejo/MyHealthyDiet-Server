/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import services.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JulenB
 */
public class IngredientEJB<Ingredient> {

    private Class<Ingredient> entityClass;
    @PersistenceContext(unitName = "MyHealthyDietPU")
    private EntityManager em;

    public IngredientEJB(Class<Ingredient> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(Ingredient entity) {
        em.persist(entity);
    }

    public void edit(Ingredient entity) {
        em.merge(entity);
    }

    public void remove(Ingredient entity) {
        em.remove(em.merge(entity));
    }

    public Ingredient find(Object id) {
        return em.find(entityClass, id);
    }

    public List<Ingredient> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    public List<Ingredient> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Ingredient> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
