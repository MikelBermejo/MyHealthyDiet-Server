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
 * @author User
 */
@Local
public interface WeightInterface {
    
    public void create(Weight entity);

    public void edit(Weight entity);

    public void remove(Weight entity);

    public Weight find(Object id);

    public List<Weight> findAll();

    public List<Weight> findRange(int[] range);

    public int count();
}
