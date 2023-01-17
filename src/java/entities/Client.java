/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Sendoa
 */
@NamedQueries({
    
    @NamedQuery(
            name="findAllClient",query="SELECT u FROM User u WHERE privilege=1"
    ),
    @NamedQuery(
            name="findClientBySearch",query="SELECT u FROM User u WHERE privilege=1 AND (login LIKE :usrValue OR email LIKE :usrValue OR fullName LIKE :usrValue)"
    ),
    @NamedQuery(
            name="findClientByStatus",query="SELECT u FROM User u WHERE privilege=1 AND status=:usrStatus"
    ),
    @NamedQuery(
            name="findClientByLogin", query="SELECT u FROM User u WHERE privilege=1 AND login=:usrLogin"
    ),
})
@Entity
@Table(name="client",schema="myhealthydietdb")
@DiscriminatorValue("1")
@XmlRootElement
public class Client extends User{

    private static final long serialVersionUID = 1L;
    private Integer age;
    
    private Float height;
    @Enumerated(EnumType.ORDINAL)
    private GenreEnum genre;
    @Enumerated(EnumType.ORDINAL)
    private GoalEnum goal;
    /**
     * @associates <{entities.Weight}>
     */
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    //@JoinColumn(name = "user_id")
    private List<Weight> weights;


    public Client(Integer user_id, String login, String email, String fullName, StatusEnum status,
                  PrivilegeEnum privilege, String password, Date lastPasswordChange, Integer age, Float height, 
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
