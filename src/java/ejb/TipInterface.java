/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Tip;
import entities.TipTypeEnum;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 2dam
 */
@Local
public interface TipInterface {
    
    public void createTip(Tip tip);
    
    public void updateTip(Tip tip);
    
    public void removeTip(Tip tip);
    
    public List<Tip> findAllTip();
    
    public Tip findTipById(Integer id);
    
    public List<Tip> findTipByType(TipTypeEnum type);
    
}
