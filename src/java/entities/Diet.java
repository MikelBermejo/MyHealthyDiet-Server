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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Julen Entity that saves the information about a Diet.
 */
@Entity
@Table(name = "diet", schema = "myhealthydietdb")
@NamedQueries({
    @NamedQuery(
            name = "findAllDiets",
            query = "SELECT d FROM Diet d ORDER BY d.dietName ASC"
    )
    ,@NamedQuery(
            name = "findDietByName",
            query = "SELECT d FROM Diet d WHERE d.dietName LIKE :text ORDER BY d.dietName ASC"
    )
    ,@NamedQuery(
            name = "findDietByGoal",
            query = "SELECT d FROM Diet d WHERE d.type = :goal ORDER BY d.dietName ASC"
    )
})
@XmlRootElement
public class Diet implements Serializable {

    //VARIABLES
    private static final long serialVersionUID = 1L;

    //Id of the Diet thats generated automatically
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer diet_id;

    /**
     * Name of the diet.
     */
    private String dietName;

    /**
     * Description of the diet.
     */
    private String description;

    /**
     * Calories of the diet.
     */
    private Float calories;

    /**
     * Proteins of the diet.
     */
    private Float proteins;

    /**
     * Lipids of the diet.
     */
    private Float lipids;

    /**
     * Carbohydrates of the diet.
     */
    private Float carbohydrates;

    /**
     * GoalType of the diet (INCREASE,DECREASE,MAINTAIN).
     */
    @Enumerated(EnumType.ORDINAL)
    private GoalEnum type;

    /**
     * Img of the diet.
     */
    @Lob
    private byte[] dietImg;

    //RELATIONS
    /**
     * @associates <{entities.Plate}>
     */
    //List with plates that we want to get it fast.
    @ManyToMany(mappedBy = "diets", fetch = FetchType.EAGER, cascade = ALL)
    private List<Plate> plates;

    /**
     * @associates <{entities.Tip}>
     */
    //List with tips in one diet.
    @OneToMany(mappedBy = "diet", fetch = FetchType.EAGER)
    private List<Tip> tips;

    //CONSTRUCTORS
    public Diet(Integer diet_id, String dietName, String description, Float calories, Float proteins, Float lipids,
            GoalEnum type, Float carbohydrates, List<Plate> plates, List<Tip> tips, byte[] dietImg) {
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
        this.dietImg = dietImg;
    }

    public Diet() {
    }

    //GETTERS AND SETTERS
    /**
     * Set the diet ID for this diet.
     *
     * @param diet_id the diet ID to set.
     */
    public void setDiet_id(Integer diet_id) {
        this.diet_id = diet_id;
    }

    /**
     * Get the diet ID for this diet.
     *
     * @return the diet ID for this diet.
     */
    public Integer getDiet_id() {
        return diet_id;
    }

    /**
     * Set the name of this diet.
     *
     * @param dietName the name to set of this diet.
     */
    public void setDietName(String dietName) {
        this.dietName = dietName;
    }

    /**
     * Get the name of this diet.
     *
     * @return the name for this diet.
     */
    public String getDietName() {
        return dietName;
    }

    /**
     * Set the description for this diet.
     *
     * @param description the description to set of this diet.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the description for this diet.
     *
     * @return the description for this diet.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the calories count for this diet.
     *
     * @param calories the calories count to set of this diet.
     */
    public void setCalories(Float calories) {
        this.calories = calories;
    }

    /**
     * Get the calories count for this diet.
     *
     * @return the calories count for this diet.
     */
    public Float getCalories() {
        return calories;
    }

    /**
     * Set the proteins count for this diet.
     *
     * @param proteins the proteins count to set of this diet.
     */
    public void setProteins(Float proteins) {
        this.proteins = proteins;
    }

    /**
     * Get the proteins count for this diet.
     *
     * @return the proteins count for this diet.
     */
    public Float getProteins() {
        return proteins;
    }

    /**
     * Set the lipids count for this diet.
     *
     * @param lipids the lipids count to set of this diet.
     */
    public void setLipids(Float lipids) {
        this.lipids = lipids;
    }

    /**
     * Get the lipids count for this diet.
     *
     * @return the lipids count for this diet.
     */
    public Float getLipids() {
        return lipids;
    }

    /**
     * Set the goal type for this diet.
     *
     * @param type the goal type to set for this diet.
     */
    public void setType(GoalEnum type) {
        this.type = type;
    }

    /**
     * Get the goal type for this diet.
     *
     * @return the goal type for this diet.
     */
    public GoalEnum getType() {
        return type;
    }

    /**
     * Set the carbohydrates count of this diet.
     *
     * @param carbohydrates the carbohydrates count to set of this diet.
     */
    public void setCarbohydrates(Float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    /**
     * Get the carbohydrates count for this diet.
     *
     * @return the carbohydrates count for this diet.
     */
    public Float getCarbohydrates() {
        return carbohydrates;
    }

    /**
     * Get the tips list for this diet.
     *
     * @return the tips list for this diet.
     */
    public List<Tip> getTips() {
        return tips;
    }

    /**
     * Set the tips list of this diet.
     *
     * @param tips the tips list to set of this diet.
     */
    public void setTips(List<Tip> tips) {
        this.tips = tips;
    }

    /**
     * Set the list of plates for this diet.
     *
     * @param plates the list of plates to set for this.
     */
    public void setPlates(List<Plate> plates) {
        this.plates = plates;
    }

    /**
     * Get the list of plates for this diet.
     *
     * @return the list of plates for this diet.
     */
    public List<Plate> getPlates() {
        return plates;
    }

    /**
     * Get the image of the diet.
     * @return the image for this diet.
     */
    public byte[] getDietImg() {
        return dietImg;
    }
    
    /**
     * Set the image for this diet.
     *
     * @param dietImg the image for this diet.
     */
    public void setDietImg(byte[] dietImg) {
        this.dietImg = dietImg;
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
