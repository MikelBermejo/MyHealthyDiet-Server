package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author JulenB
 * Entity thats created by the relation of a Client and a Diet and saves the information of them.
 */
@Entity
public class ClientDiet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Client client_id;
    private Diet diet_id;
    private Boolean isActive;
    
    public ClientDiet(Client client_id, Diet diet_id, Boolean isActive) {
    this.client_id = client_id;
    this.diet_id = diet_id;
    this.isActive = isActive;
    }
    
    public ClientDiet() {
    }

        public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }

    public Client getClient_id() {
        return client_id;
    }

    public void setDiet_id(Diet diet_id) {
        this.diet_id = diet_id;
    }

    public Diet getDiet_id() {
        return diet_id;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (client_id != null ? client_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientDiet)) {
            return false;
        }
        ClientDiet other = (ClientDiet) object;
        if ((this.client_id == null && other.client_id != null) || (this.client_id != null && !this.client_id.equals(other.client_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
