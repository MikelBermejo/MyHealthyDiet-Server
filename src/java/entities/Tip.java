/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sendoa
 */
@NamedQueries({
    
    @NamedQuery(
            name="findAllTip",query="SELECT t FROM Tip t"
    ),
    @NamedQuery(
            name="findTipByType",query="SELECT t FROM Tip t WHERE type = :tipType"
    ),
    
})
@Entity
@Table(name="tip",schema="myhealthydietdb")
@XmlRootElement
public class Tip implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tip_id;

    private String tipText;
    @Enumerated(EnumType.ORDINAL)
    private TipTypeEnum type;
    
    @ManyToOne
    private Diet diet;

    public Tip(Integer tip_id, String tipText, TipTypeEnum type) {
        this.tip_id = tip_id;
        this.tipText = tipText;
        this.type = type;
    }
    
    public Tip() {
    }


    public void setTip_id(Integer tip_id) {
        this.tip_id = tip_id;
    }

    public Integer getTip_id() {
        return tip_id;
    }

    public void setTipText(String tipText) {
        this.tipText = tipText;
    }

    public String getTipText() {
        return tipText;
    }

    public void setType(TipTypeEnum type) {
        this.type = type;
    }

    public TipTypeEnum getType() {
        return type;
    }
    
    @XmlTransient
    public Diet getDiet() {
        return diet;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }
    
    

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tip)) {
            return false;
        }
        Tip other = (Tip) object;
        if ((this.tip_id == null && other.tip_id != null) || (this.tip_id != null && !this.tip_id.equals(other.tip_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
