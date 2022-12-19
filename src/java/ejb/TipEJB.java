/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Tip;
import entities.TipTypeEnum;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 2dam
 */
@Stateless
public class TipEJB implements TipInterface{

    /**
     * EntityManager for MyHealthyDietPU persistence unit.
     */
    @PersistenceContext(unitName = "MyHealthyDietPU")
    private EntityManager em;
    
    @Override
    public void createTip(Tip tip) {
        em.persist(tip);
    }

    @Override
    public void updateTip(Tip tip) {
        if(!em.contains(tip))
            em.merge(tip);
        em.flush();
    }

    @Override
    public void removeTip(Tip tip) {
        em.remove(em.merge(tip));
    }

    @Override
    public List<Tip> findAllTip() {
        List<Tip> tips;
        
        tips = em.createNamedQuery("findAllTip").getResultList();
        
        return tips;
    }

    @Override
    public Tip findTipById(Integer id) {
        Tip tip;
        
        tip = em.find(Tip.class, id);
        
        return tip;
    }

    @Override
    public List<Tip> findTipByType(TipTypeEnum type) {
        List<Tip> tips;
        
        tips = em.createNamedQuery("findTipByType").setParameter("tipType", type).getResultList();
        
        return tips;
    }
    
}
