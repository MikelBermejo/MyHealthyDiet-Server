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
import javax.ejb.Local;

/**
 *
 * @author Mikel
 */
@Local
public interface IngredientInterface {
    
    /**
     * Method that creates an Ingredient.
     * @param entity Ingredient to create.
     */
    public void createIngredient(Ingredient entity) throws CreateException;
    
    /**
     * Method to edit an Ingredient.
     * @param entity Ingredient to edit.
     */
    public void editIngredient(Ingredient entity) throws UpdateException;
    
    /**
     * Method to remove an Ingredient.
     * @param entity Ingredient to remove.
     */
    public void removeIngredient(Ingredient entity) throws DeleteException;
    
    /**
     * Method to search an Ingredient by id.
     * @param id id of the ingredient to find.
     * @return ingredient finded.
     */
    public Ingredient findIngredient(Object id) throws ReadException;
    
    /**
     * Method to show all the Ingredients.
     * @return list of the ingredients finded.
     */
    public List<Ingredient> findAllIngredients() throws ReadException;
    
    /**
     * Method to search an Ingredient by name.
     * @param ingredientName name of the ingredients to search.
     * @return List of the ingredient finded
     */
    public List<Ingredient> findIngredientsByName(String ingredientName) throws ReadException;
}
