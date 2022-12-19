/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.User;

/**
 *
 * @author 2dam
 */
public interface UserInterface {
    
    public User signIn(String login, String password);
    
    public void lastSignIn(Integer id);
    
}
