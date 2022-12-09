/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 *
 * @author JulenB
 */
@Entity
public class Client extends User implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer age;
    private Float height;
    private GenreEnum genre;
    private GoalEnum goal;


    public Client(Integer user_id, String login, String email, String fullName, StatusEnum status,
                  PrivilegeEnum privilege, Integer password, Timestamp lastPasswordChange, Integer age, Float height, 
                  GenreEnum genre, GoalEnum goal, List<Weight> weights) {
        // TODO Implement this method
        super(user_id, login, email, fullName, status, privilege, password, lastPasswordChange);
        this.age = age;
        this.height = height;
        this.genre = genre;
        this.goal = goal;
        this.weights = weights;
    }


    public Client() {
    }

    /**
     * @associates <{client.Weight}>
     */
    private List<Weight> weights;


    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getHeight() {
        return height;
    }

    public void setGenre(GenreEnum genre) {
        this.genre = genre;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public void setGoal(GoalEnum goal) {
        this.goal = goal;
    }

    public GoalEnum getGoal() {
        return goal;
    }

    public void setWeights(List<Weight> weights) {
        this.weights = weights;
    }

    public List<Weight> getWeights() {
        return weights;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (super.getUser_id() != null ? getUser_id().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((super.getUser_id() == null && other.getUser_id() != null) || (super.getUser_id() != null && !super.getUser_id().equals(other.getUser_id()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
