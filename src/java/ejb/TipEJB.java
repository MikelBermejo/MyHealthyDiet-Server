/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Tip;
import entities.TipTypeEnum;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is the stateless EJB that implements the TipInterface
 *
 * @author Sendoa
 */
@Stateless
public class TipEJB implements TipInterface {

    /**
     * EntityManager for MyHealthyDietPU persistence unit.
     */
    @PersistenceContext(unitName = "MyHealthyDietPU")
    private EntityManager em;

    /**
     * This method creates a new tip
     *
     * @param tip The tip entity containing the data
     * @throws CreateException Exception thrown when any error ocurrs during
     * creation
     */
    @Override
    public void createTip(Tip tip) throws CreateException {
        try {
            em.persist(tip);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * This method updates an already exising tip
     *
     * @param tip The tip containing all the new data
     * @throws UpdateException Exception thrown when any error ocurrs during the
     * update
     */
    @Override
    public void updateTip(Tip tip) throws UpdateException {
        try {
            if (!em.contains(tip)) {
                em.merge(tip);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * This method removes an existing tip
     *
     * @param tip The tip entity that is going to be removed
     * @throws DeleteException Exception thrown when any error ocurrs during the
     * removal
     */
    @Override
    public void removeTip(Tip tip) throws DeleteException {
        try {
            em.remove(em.merge(tip));
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * This method finds all the tips
     *
     * @return A list with all the tips
     * @throws ReadException Exception thrown when any error ocurrs during the
     * query
     */
    @Override
    public List<Tip> findAllTip() throws ReadException {
        List<Tip> tips;

        try {
            tips = em.createNamedQuery("findAllTip").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }

        return tips;
    }

    /**
     * This method finds a single tip by id
     *
     * @param id the id of the tip to find
     * @return The tip with the same id
     * @throws ReadException Exception thrown when any error ocurrs during the
     * query
     */
    @Override
    public Tip findTipById(Integer id) throws ReadException {
        Tip tip;

        try {
            tip = em.find(Tip.class, id);
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return tip;
    }

    /**
     * This methods finds tips by their type
     *
     * @param tipType the type of the tips (APP or NUTRITION)
     * @return A list of the tips with that type
     * @throws ReadException Exception thrown when any error ocurrs during the
     * query
     */
    @Override
    public List<Tip> findTipByType(TipTypeEnum tipType) throws ReadException {
        List<Tip> tips;

        try {
            tips = em.createNamedQuery("findTipByType").setParameter("tipType", tipType).getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
        return tips;
    }

}
