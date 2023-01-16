package ejb;

import entities.MealEnum;
import entities.Plate;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
import javax.ejb.Local;

/**
 * @author HaizeaF
 * Interface of the plate model. 
 */
@Local
public interface PlateInterface {
    public void createPlate(Plate plate) throws CreateException;
    public void updatePlate(Plate plate) throws UpdateException;
    public void removePlate(Plate plate) throws DeleteException;
    public Plate findPlate(Integer plate_id) throws ReadException;
    public List<Plate> findAllPlates() throws ReadException;
    public List<Plate> findPlatesByName(String PlateName) throws ReadException;
    public List<Plate> findPlatesByMealType(MealEnum mealType) throws ReadException;
    public List<Plate> findPlatesIfVegetarian() throws ReadException;
}
