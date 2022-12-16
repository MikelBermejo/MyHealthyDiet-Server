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
public class WeightEJB<Weight> {

    private Class<Weight> entityClass;
    @PersistenceContext(unitName = "MyHealthyDietPU")
    private EntityManager em;

    public WeightEJB(Class<Weight> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(Weight entity) {
        em.persist(entity);
    }

    public void edit(Weight entity) {
        em.merge(entity);
    }

    public void remove(Weight entity) {
        em.remove(em.merge(entity));
    }

    public Weight find(Object id) {
        return em.find(entityClass, id);
    }

    public List<Weight> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    public List<Weight> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<Weight> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
