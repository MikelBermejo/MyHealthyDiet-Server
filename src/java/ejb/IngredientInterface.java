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
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author Mikel
 */
@Local
public interface IngredientInterface {
    
    public void createIngredient(Ingredient entity) throws CreateException;

    public void editIngredient(Ingredient entity) throws UpdateException;

    public void removeIngredient(Ingredient entity) throws DeleteException;

    public Ingredient findIngredient(Object id) throws ReadException;

    public List<Ingredient> findAllIngredients() throws ReadException;
    
    public List<Ingredient> findIngredientsByName(String ingredientName) throws ReadException;
}
