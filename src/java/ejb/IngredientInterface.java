/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Ingredient;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mikel
 */
@Local
public interface IngredientInterface {
    
    public void create(Ingredient entity);

    public void edit(Ingredient entity);

    public void remove(Ingredient entity);

    public Ingredient find(Object id);

    public List<Ingredient> findAll();

    public List<Ingredient> findRange(int[] range);

    public int count();
}
