package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

/**
 *
 * @author JulenB Entity thats created by the relation of a Client and a Diet
 * and saves the information of them.
 */
@Entity
public class ClientDiet implements Serializable {
    
    //VARIABLES

    private static final long serialVersionUID = 1L;
    
    //EmbeddedId composed by the client and the diet.
    @EmbeddedId
    private ClientDiet clientDietId;
    
    private Boolean isActive;
    
    //RELATIONS
    
    /**
     * @associates <{entities.Client}>
     */
    @MapsId("client_id")
    @ManyToOne
    private Client client;
    
    /**
     * @associates <{entities.Diet}>
     */
    @MapsId("diet_id")
    @ManyToOne
    private Diet diet;

    //CONSTRUCTORS

    public ClientDiet(ClientDiet clientDietId, Client client, Diet diet, Boolean isActive) {
        this.client = client;
        this.diet = diet;
        this.clientDietId = clientDietId;
        this.isActive = isActive;
    }

    public ClientDiet() {
    }
    
    //GETTERS AND SETTERS

    public ClientDiet getClientDietId() {
        return clientDietId;
    }

    public void setClientDietId(ClientDiet clientDietId) {
        this.clientDietId = clientDietId;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    public Diet getDiet() {
        return diet;
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
        hash += (clientDietId != null ? clientDietId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClientDiet)) {
            return false;
        }
        ClientDiet other = (ClientDiet) object;
        if ((this.clientDietId == null && other.clientDietId != null) || (this.clientDietId != null && !this.clientDietId.equals(other.clientDietId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
