/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Ingredient;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author Mikel
 */
@Local
public interface IngredientInterface {
    
    public void createIngredient(Ingredient entity);

    public void editIngredient(Ingredient entity);

    public void removeIngredient(Ingredient entity);

    public Ingredient findIngredient(Object id);

    public List<Ingredient> findAllIngredients();
    
    public List<Ingredient> findIngredientsByName(String ingredientName);
}
