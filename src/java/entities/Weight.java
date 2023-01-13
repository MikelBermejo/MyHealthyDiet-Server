/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mikel
 */
@NamedQueries({
    @NamedQuery(
            name ="findAllWeights", query="SELECT w FROM Weight w ORDER BY w.date ASC"
    ),
    @NamedQuery(
            name ="findAllWeightsByClient", query="SELECT w FROM Weight w JOIN w.client c JOIN c.weights w Where c.user_id=:id ORDER BY w.date ASC"
    )
})
@Entity
@Table(name="weight",schema="myhealthydietdb")
@XmlRootElement
public class Weight implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer weight_id;
    
    /**
     * Weight of the client in kg
     */
    private Float weight;
    
    /**
     * Date that the client saved his weight
     */
    @Temporal(TemporalType.TIMESTAMP)    
    private Date date;
    
    @ManyToOne
    private Client client;

    public Weight(Float weight, Date date, Integer weight_id) {
        this.weight_id = weight_id;
        this.weight = weight;
        this.date = date;
    }

    public Weight() {

    }
    
    public void setWeight_id(Integer weight_id) {
        this.weight_id = weight_id;
    }

    public Integer getWeight_id() {
        return weight_id;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getWeight() {
        return weight;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    @XmlTransient
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (weight_id != null ? weight_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Weight)) {
            return false;
        }
        Weight other = (Weight) object;
        if ((this.weight_id == null && other.weight_id != null) || (this.weight_id != null && !this.weight_id.equals(other.weight_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
