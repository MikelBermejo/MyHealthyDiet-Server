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
public class Diet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer diet_id;
    private String dietName;
    private String description;
    private Float calories;
    private Float proteins;
    private Float lipids;
    private GoalEnum type;
    private Float carbohydrates;


    public Diet(Integer diet_id, String dietName, String description, Float calories, Float proteins, Float lipids,
                GoalEnum type, Float carbohydrates, List<Plate> plates, List<Tip> tips) {
        this.diet_id = diet_id;
        this.dietName = dietName;
        this.description = description;
        this.calories = calories;
        this.proteins = proteins;
        this.lipids = lipids;
        this.type = type;
        this.carbohydrates = carbohydrates;
        this.plates = plates;
        this.tips = tips;
    }
    
    public Diet() {
    }


    /**
     * @associates <{client.Plate}>
     */
    private List<Plate> plates;


    /**
     * @associates <{client.Tip}>
     */
    private List<Tip> tips;


    public void setDiet_id(Integer diet_id) {
        this.diet_id = diet_id;
    }

    public Integer getDiet_id() {
        return diet_id;
    }

    public void setDietName(String dietName) {
        this.dietName = dietName;
    }

    public String getDietName() {
        return dietName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public Float getCalories() {
        return calories;
    }

    public void setProteins(Float proteins) {
        this.proteins = proteins;
    }

    public Float getProteins() {
        return proteins;
    }

    public void setLipids(Float lipids) {
        this.lipids = lipids;
    }

    public Float getLipids() {
        return lipids;
    }

    public void setType(GoalEnum type) {
        this.type = type;
    }

    public GoalEnum getType() {
        return type;
    }

    public void setCarbohydrates(Float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Float getCarbohydrates() {
        return carbohydrates;
    }

    public void setPlates(List<Plate> plates) {
        this.plates = plates;
    }

    public List<Plate> getPlates() {
        return plates;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (diet_id != null ? diet_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diet)) {
            return false;
        }
        Diet other = (Diet) object;
        if ((this.diet_id == null && other.diet_id != null) || (this.diet_id != null && !this.diet_id.equals(other.diet_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
