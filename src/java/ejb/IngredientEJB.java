/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Ingredient;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
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
    public void createIngredient(Ingredient entity) throws CreateException {
        try{
            em.persist(entity);
        }catch(Exception e){
            throw new CreateException(e.getMessage());
        }   
    }

    /**
     * 
     * @param entity 
     */
    public void editIngredient(Ingredient entity) throws UpdateException {
        try{
            if(!em.contains(entity)) {
                     em.merge(entity);
             }
                 em.flush();
        }catch(Exception e){
            throw new UpdateException(e.getMessage());
        } 
    }

    /**
     * 
     * @param entity 
     */
    public void removeIngredient(Ingredient entity) throws DeleteException {
        try{
            em.remove(em.merge(entity));
        }catch(Exception e){
            throw new DeleteException(e.getMessage());
        } 
    }

    /**
     * 
     * @param id
     * @return 
     */
    public Ingredient findIngredient(Object id) throws ReadException {
        try{
            return em.find(Ingredient.class, id);
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        } 
    }

    /**
     * 
     * @return 
     */
    public List<Ingredient> findAllIngredients() throws ReadException {
        try{
            return em.createNamedQuery("findAllIngredients").getResultList();
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        } 
    }
    /**
     * 
     * @param ingredientName
     * @return 
     */
    public List<Ingredient> findIngredientsByName(String ingredientName) throws ReadException {
        try{
            return em.createNamedQuery("findIngredientsByName").setParameter("ingredientName", "%"+ingredientName+"%").getResultList();
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        } 
    }
}
