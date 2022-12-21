/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.MealEnum;
import entities.Plate;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author HaizeaF
 */
@Stateless
public class PlateEJB implements PlateInterface{

    
    @PersistenceContext(unitName = "MyHealthyDietPU")
    private EntityManager em;

    @Override
    public void createPlate(Plate plate) {
        em.persist(plate);
    }

    @Override
    public void updatePlate(Plate plate) {
        if(!em.contains(plate)) {
                em.merge(plate);
        }
            em.flush();
    }

    @Override
    public void removePlate(Plate plate) {
        em.remove(plate);
    }

    @Override
    public Plate findPlate(Integer plate_id) {
        return em.find(Plate.class, plate_id);
    }

    @Override
    public List<Plate> findAllPlates() {
        return em.createNamedQuery("findAllPlates").getResultList();
    }

    @Override
    public List<Plate> findPlatesByName(String plateName) {
        return em.createNamedQuery("findPlatesByName").setParameter("name", "%" + plateName + "%").getResultList();
    }

    @Override
    public List<Plate> findPlatesByIngredient(Integer ingredient_id) {
        return em.createNamedQuery("findPlatesByIngredient").setParameter("idIngredient", ingredient_id).getResultList();
    }

    @Override
    public List<Plate> findPlatesByMealType(MealEnum mealType) {
        return em.createNamedQuery("findPlatesByMealType").setParameter("mealType", mealType).getResultList();
    }

    @Override
    public List<Plate> findPlatesIfVegetarian() {
        return em.createNamedQuery("findPlatesIfVegetarian").getResultList();
    }
   
}
