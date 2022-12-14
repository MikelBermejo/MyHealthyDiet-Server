/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mikel
 */
@NamedQueries({
    @NamedQuery(
            name ="findAllIngredients", query="SELECT i FROM Ingredient i ORDER BY i.ingredientName ASC"
    ),
    @NamedQuery(
            name="findIngredientName", query="SELECT i FROM Ingredient i WHERE i.ingredientName=:ingredientName"
    )
})
@Entity
@Table(name = "ingredient", schema = "myhealthydietdb")
@XmlRootElement
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Id of the entity Ingredient
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer ingredient_id;
    
    /**
     * Name of the ingredient
     */

    private String ingredientName;
    
    /**
     * Enumeration for the type of food
     */

    @Enumerated(EnumType.ORDINAL)
    private FoodTypeEnum foodType;
    
    /**
     * List to join plate with ingredient
     */
    @ManyToMany
    @JoinTable(schema="myhealthydietdb", name = "PlateIngredient")
    @XmlTransient
    private List<Plate> plates;
    
    /**
     * Boolean that shows if it is in season or if is not
     */

    private Boolean isInSeason;
    
    public Ingredient(Integer ingredient_id, String ingredientName, FoodTypeEnum foodType,
                      List<Plate> plates, Boolean isInSeason) {
        this.ingredient_id = ingredient_id;
        this.ingredientName = ingredientName;
        this.foodType = foodType;
        this.plates = plates;
        this.isInSeason = isInSeason;
    }
    
    public Ingredient() {
    }

    public Integer getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(Integer ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public FoodTypeEnum getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodTypeEnum foodType) {
        this.foodType = foodType;
    }


    @XmlTransient
    public List<Plate> getPlates() {
        return plates;
    }

    public void setPlates(List<Plate> plates) {
        this.plates = plates;
    }

    public Boolean getIsInSeason() {
        return isInSeason;
    }

    public void setIsInSeason(Boolean isInSeason) {
        this.isInSeason = isInSeason;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ingredient_id != null ? ingredient_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ingredient)) {
            return false;
        }
        Ingredient other = (Ingredient) object;
        if ((this.ingredient_id == null && other.ingredient_id != null) || (this.ingredient_id != null && !this.ingredient_id.equals(other.ingredient_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
