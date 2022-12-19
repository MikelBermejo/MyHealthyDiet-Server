/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 2dam
 */
@Stateless
public class UserEJB implements UserInterface{

    /**
     * EntityManager for MyHealthyDietPU persistence unit.
     */
    @PersistenceContext(unitName = "MyHealthyDietPU")
    private EntityManager em;
    
    @Override
    public User signIn(String login, String password) {
        User user;
        
        user = (User) em.createNamedQuery("signIn").setParameter("loginUsr", login).setParameter("passUsr", password).getSingleResult();
        
        return user;
    }

    @Override
    public void lastSignIn(Integer id) {
        em.createNamedQuery("lastSignIn");
    }
    
}
