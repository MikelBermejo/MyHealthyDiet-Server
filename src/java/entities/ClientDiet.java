package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author JulenB Entity thats created by the relation of a Client and a Diet
 * and saves the information of them.
 */
@Entity
@Table(name = "clientdiet", schema = "myhealthydietdb")
@NamedQueries({
    @NamedQuery(
            name = "findAllClientDiets",
            query = "SELECT c FROM ClientDiet c ORDER BY NEWID()"
    ),@NamedQuery(
            name = "findClientDietsForYou",
            query = "SELECT c FROM ClientDiet c"
    )
})
@XmlRootElement
public class ClientDiet implements Serializable {

    //VARIABLES
    private static final long serialVersionUID = 1L;

    //EmbeddedId composed by the client and the diet.
    @EmbeddedId
    private ClientDietId clientDietId;

    /**
     * Boolean if the diet is active for the Client.
     */
    private Boolean isActive;

    //RELATIONS
    /**
     * @associates <{entities.Client}>
     */
    /**
     * Id of a client.
     */
    @MapsId("client_id")
    @ManyToOne
    private Client client;

    /**
     * @associates <{entities.Diet}>
     */
    /**
     * Id of a diet.
     */
    @MapsId("diet_id")
    @ManyToOne
    private Diet diet;

    //CONSTRUCTORS
    public ClientDiet(ClientDietId clientDietId, Client client, Diet diet, Boolean isActive) {
        this.client = client;
        this.diet = diet;
        this.clientDietId = clientDietId;
        this.isActive = isActive;
    }

    public ClientDiet() {
    }

    //GETTERS AND SETTERS
    /**
     * Get the clientDietId .
     *
     * @return the clientDietId.
     */
    public ClientDietId getClientDietId() {
        return clientDietId;
    }

    /**
     * Set the clientDietId.
     *
     * @param clientDietId the clientDietId to set.
     */
    public void setClientDietId(ClientDietId clientDietId) {
        this.clientDietId = clientDietId;
    }
    
    /**
     * Get the client .
     * @return the client.
     */
    public Client getClient() {
        return client;
    }
    
    /**
     * Set the client.
     *
     * @param client the diet to set.
     */
    public void setClient(Client client) {
        this.client = client;
    }
    
    /**
     * Get the diet .
     * @return the diet.
     */
    public Diet getDiet() {
        return diet;
    }
    
    /**
     * Set the diet.
     *
     * @param diet the diet to set.
     */
    public void setDiet(Diet diet) {
        this.diet = diet;
    }

    /**
     * Set the is active for the diet and client.
     *
     * @param isActive if the diet is active for a client to set.
     */
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Get if a diet is active for a client.
     *
     * @return if the diet is active for a client.
     */
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
