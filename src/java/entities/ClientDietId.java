/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author JulenB
 * Class that constains the two ids, client and diet.
 */
@Embeddable
public class ClientDietId implements Serializable{
    private Integer client_id;
    
    private Integer diet_id;
}
