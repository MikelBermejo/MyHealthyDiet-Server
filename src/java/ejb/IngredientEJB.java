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
    public void createIngredient(Ingredient entity) {
        em.persist(entity);
    }

    /**
     * 
     * @param entity 
     */
    public void editIngredient(Ingredient entity) {
       if(!em.contains(entity)) {
                em.merge(entity);
        }
            em.flush();
    }

    /**
     * 
     * @param entity 
     */
    public void removeIngredient(Ingredient entity) {
        em.remove(em.merge(entity));
    }

    /**
     * 
     * @param id
     * @return 
     */
    public Ingredient findIngredient(Object id) {
        return em.find(Ingredient.class, id);
    }

    /**
     * 
     * @return 
     */
    public List<Ingredient> findAllIngredients() {
        return em.createNamedQuery("findAllIngredients").getResultList();
    }
    /**
     * 
     * @param ingredientName
     * @return 
     */
    public List<Ingredient> findIngredientsByName(String ingredientName){
        return em.createNamedQuery("findIngredientsByName").setParameter("ingredientName", "%"+ingredientName+"%").getResultList();
    }
}
