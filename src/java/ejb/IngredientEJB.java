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
     * Method that creates an Ingredient.
     * @param entity Ingredient to create.
     */
    public void createIngredient(Ingredient entity) throws CreateException {
        try{
            em.persist(entity);
        }catch(Exception e){
            throw new CreateException(e.getMessage());
        }   
    }

    /**
     * Method to edit an Ingredient.
     * @param entity Ingredient to edit.
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
     * Method to remove an Ingredient.
     * @param entity Ingredient to remove.
     */
    public void removeIngredient(Ingredient entity) throws DeleteException {
        try{
            em.remove(em.merge(entity));
        }catch(Exception e){
            throw new DeleteException(e.getMessage());
        } 
    }

    /**
     * Method to search an Ingredient by id.
     * @param id id of the ingredient to find.
     * @return ingredient finded.
     */
    public Ingredient findIngredient(Object id) throws ReadException {
        try{
            return em.find(Ingredient.class, id);
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        } 
    }

    /**
     * Method to show all the Ingredients.
     * @return list of the ingredients finded.
     */
    public List<Ingredient> findAllIngredients() throws ReadException {
        try{
            return em.createNamedQuery("findAllIngredients").getResultList();
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        } 
    }
    /**
     * Method to search an Ingredient by name.
     * @param ingredientName name of the ingredients to search.
     * @return List of the ingredient finded
     */
    public List<Ingredient> findIngredientsByName(String ingredientName) throws ReadException {
        try{
            return em.createNamedQuery("findIngredientsByName").setParameter("ingredientName", "%"+ingredientName+"%").getResultList();
        }catch(Exception e){
            throw new ReadException(e.getMessage());
        } 
    }
}
