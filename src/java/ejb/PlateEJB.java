/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.MealEnum;
import entities.Plate;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author HaizeaF
 * EJB that manages the plates
 */
@Stateless
public class PlateEJB implements PlateInterface{
    
    /**
     * The EntityManager for the persistance unit
     */
    @PersistenceContext(unitName = "MyHealthyDietPU")
    private EntityManager em;
    
    /**
     * Method that creates a new plate
     * @param plate The plate we want to create
     * @throws CreateException Thrown when an exception occurs while creating a plate
     */
    @Override
    public void createPlate(Plate plate) throws CreateException {
        try {
            em.persist(plate);
        } catch (Exception e) {
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Method that updates the data of a plate
     * @param plate The plate we want to update
     * @throws UpdateException Thrown when an exception occurs while updating a plate
     */
    @Override
    public void updatePlate(Plate plate) throws UpdateException {
        try {
            if(!em.contains(plate)) {
                em.merge(plate);
            }
            em.flush();
        } catch (Exception e) {
            throw new UpdateException(e.getMessage());
        }
    }
    
    /**
     * Method that deletes a plate
     * @param plate The plate we want to delete
     * @throws DeleteException Thrown when an exception occurs while deleting a plate
     */
    @Override
    public void removePlate(Plate plate) throws DeleteException {
        try {
            em.remove(em.merge(plate));
        } catch (Exception e) {
            throw new DeleteException(e.getMessage());
        }
    }
    
    /**
     * Method that gets a plate by id
     * @param plate_id The id of the plate we want to find
     * @return The plate we want to obtain
     * @throws ReadException Thrown when an exception occurs while reading a plate
     */
    @Override
    public Plate findPlate(Integer plate_id) throws ReadException {
        try {
            return em.find(Plate.class, plate_id);
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }

    /**
     * Method that gets all the plates
     * @return All the plates
     * @throws ReadException Thrown when an exception occurs while reading the plates
     */
    @Override
    public List<Plate> findAllPlates() throws ReadException {
        try {
            return em.createNamedQuery("findAllPlates").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }
    
    /**
     * Method that gets the plates whose names contain specific characters
     * @param plateName The characters that must contain the names of the plates that we want to find
     * @return The plates we want to obtain
     * @throws ReadException Thrown when an exception occurs while reading the plates
     */
    @Override
    public List<Plate> findPlatesByName(String plateName) throws ReadException {
        try {
            return em.createNamedQuery("findPlatesByName").setParameter("name", "%" + plateName + "%").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }
    
    /**
     * Method that obtains the plates depending on the time of day they belong to (by mealType)
     * @param mealType The meal type of the plates that we want to find
     * @return The plates we want to obtain
     * @throws ReadException Thrown when an exception occurs while reading the plates
     */
    @Override
    public List<Plate> findPlatesByMealType(MealEnum mealType) throws ReadException {
        try {
            return em.createNamedQuery("findPlatesByMealType").setParameter("mealType", mealType).getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }
    
    /**
     * Method that obtains all the vegetarian plates
     * @return All the vegetarian plates
     * @throws ReadException Thrown when an exception occurs while reading the plates
     */
    @Override
    public List<Plate> findPlatesIfVegetarian() throws ReadException {
        try {
            return em.createNamedQuery("findPlatesIfVegetarian").getResultList();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }
    }
   
}
