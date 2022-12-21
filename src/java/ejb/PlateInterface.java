/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Ingredient;
import entities.MealEnum;
import entities.Plate;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author HaizeaF
 */
@Local
public interface PlateInterface {
    public void createPlate(Plate plate);
    public void updatePlate(Plate plate);
    public void removePlate(Plate plate);
    public Plate findPlate(Integer plate_id);
    public List<Plate> findAllPlates();
    public List<Plate> findPlatesByName(String PlateName);
    public List<Plate> findPlatesByIngredient(Integer ingredient_id);
    public List<Plate> findPlatesByMealType(MealEnum mealType);
    public List<Plate> findPlatesIfVegetarian();
}
