/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Weight;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mikel
 */
@Local
public interface WeightInterface {
    
    public void createWeight(Weight entity) throws CreateException;

    public void editWeight(Weight entity) throws UpdateException;

    public void removeWeight(Weight entity) throws DeleteException;

    public Weight findWeight(Object id) throws ReadException;

    public List<Weight> findAllWeights() throws ReadException;
}
