/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author JulenB
 */
@Entity
public class Plate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer plate_id;
    private String plateName;
    private Float calories;
    private Float carbohydrates;
    private Float lipids;
    private Float proteins;
    private MealEnum mealType;


    public Plate(Integer plate_id, String plateName, Float calories, Float carbohydrates, Float lipids, Float proteins,
                 MealEnum mealType, List<Ingredient> ingredients, Boolean isVegetarian, List<Diet> diets) {
        this.plate_id = plate_id;
        this.plateName = plateName;
        this.calories = calories;
        this.carbohydrates = carbohydrates;
        this.lipids = lipids;
        this.proteins = proteins;
        this.mealType = mealType;
        this.ingredients = ingredients;
        this.isVegetarian = isVegetarian;
        this.diets = diets;
    }
    
    public Plate() {
    }

    /**
     * @associates <{client.Ingredient}>
     */
    private List<Ingredient> ingredients;
    private Boolean isVegetarian;

    /**
     * @associates <{client.Diet}>
     */
    private List<Diet> diets;

    public void setPlate_id(Integer plate_id) {
        this.plate_id = plate_id;
    }

    public Integer getPlate_id() {
        return plate_id;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public Float getCalories() {
        return calories;
    }

    public void setCarbohydrates(Float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Float getCarbohydrates() {
        return carbohydrates;
    }

    public void setLipids(Float lipids) {
        this.lipids = lipids;
    }

    public Float getLipids() {
        return lipids;
    }

    public void setProteins(Float proteins) {
        this.proteins = proteins;
    }

    public Float getProteins() {
        return proteins;
    }

    public void setMealType(MealEnum mealType) {
        this.mealType = mealType;
    }

    public MealEnum getMealType() {
        return mealType;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plate_id != null ? plate_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plate)) {
            return false;
        }
        Plate other = (Plate) object;
        if ((this.plate_id == null && other.plate_id != null) || (this.plate_id != null && !this.plate_id.equals(other.plate_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       return super.toString();
    }
    
}
