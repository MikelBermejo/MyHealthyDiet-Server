/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Tip;
import entities.TipTypeEnum;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sendoa
 */
@Local
public interface TipInterface {
    
    public void createTip(Tip tip) throws CreateException;
    
    public void updateTip(Tip tip) throws UpdateException;
    
    public void removeTip(Tip tip) throws DeleteException;
    
    public List<Tip> findAllTip() throws ReadException;
    
    public Tip findTipById(Integer id) throws ReadException;
    
    public List<Tip> findTipByType(TipTypeEnum tipType) throws ReadException;
    
}
