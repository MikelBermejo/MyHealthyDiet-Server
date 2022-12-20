/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Weight;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Mikel
 */
@Local
public interface WeightInterface {
    
    public void createWeight(Weight entity);

    public void editWeight(Weight entity);

    public void removeWeight(Weight entity);

    public Weight findWeight(Object id);

    public List<Weight> findAllWeights();
}
