package entities;

import java.io.Serializable;
import java.util.List;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
 * @author HaizeaF
 * Entity that contains the information of a plate.
 */
@Entity
@Table(name = "plate", schema = "myhealthydietdb")
@XmlRootElement
@NamedQueries({
    @NamedQuery(
        name="findPlatesByName", query="SELECT p FROM Plate p WHERE p.plateName LIKE :name ORDER BY p.plateName ASC"),
    @NamedQuery(
        name="findAllPlates", query="SELECT p FROM Plate p ORDER BY p.plateName ASC"),
    @NamedQuery(
        name="findPlatesByIngredient", query="SELECT p FROM Plate p JOIN p.ingredients ingredient JOIN ingredient.plates p WHERE ingredient.ingredient_id = :idIngredient ORDER BY p.plateName ASC"),
    @NamedQuery(
        name="findPlatesByMealType", query="SELECT p FROM Plate p WHERE p.mealType = :mealType ORDER BY p.plateName ASC"),
    @NamedQuery(
        name="findPlatesIfVegetarian", query="SELECT p FROM Plate p WHERE p.isVegetarian = TRUE ORDER BY p.plateName ASC")
        
})
public class Plate implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Identifier of the plate.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer plate_id;

    /**
     * Name of the plate.
     */

    private String plateName;

    /**
     * KiloCalories in the plate.
     */

    private Float calories;

    /**
     * Carbohydrates in the plate.
     */

    private Float carbohydrates;

    /**
     * Lipids in the plate.
     */

    private Float lipids;

    /**
     * Proteins in the plate.
     */

    private Float proteins;

    /**
     * What meal of the day is.
     */

    @Enumerated(EnumType.ORDINAL)
    private MealEnum mealType;
    
    /**
     * Defines if the plate is vegetarian or not.
     */

    private Boolean isVegetarian;

    /**
     * List of the ingredients the plate has.
     */
    @ManyToMany(mappedBy = "plates", cascade=ALL, fetch = FetchType.EAGER)
    private List<Ingredient> ingredients;
    
    /**
     * List of the diets the plate is on.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema="myhealthydietdb", name = "dietplate")
    @XmlTransient
    private List<Diet> diets;

    public Plate(String plateName, Float calories, Float carbohydrates, Float lipids, Float proteins,
            MealEnum mealType, List<Ingredient> ingredients, Boolean isVegetarian, List<Diet> diets) {
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

    public Boolean getIsVegetarian() {
        return isVegetarian;
    }

    public void setIsVegetarian(Boolean isVegetarian) {
        this.isVegetarian = isVegetarian;
    }


    @XmlTransient
    public List<Diet> getDiets() {
        return diets;
    }

    public void setDiets(List<Diet> diets) {
        this.diets = diets;
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