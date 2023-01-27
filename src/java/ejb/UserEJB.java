/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.User;
import exceptions.ReadException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import cryptography.HashMD5;
import files.Asymmetric;
import javax.xml.bind.DatatypeConverter;

/**
 * This is the stateless EJB that implements the UserInterface
 *
 * @author Sendoa
 */
@Stateless
public class UserEJB implements UserInterface {

    /**
     * EntityManager for MyHealthyDietPU persistence unit.
     */
    @PersistenceContext(unitName = "MyHealthyDietPU")
    private EntityManager em;

    /**
     * This method finds a user using the login and password
     *
     * @param login the login of the user
     * @param password the password of the user
     * @return the user if it finds one
     * @throws ReadException Exception thrown when any error ocurrs during the
     * query
     */
    @Override
    public User signIn(String login, String password) throws ReadException {
        User user;

        try {
            byte[] passwordBytes = new Asymmetric().decrypt(DatatypeConverter.parseHexBinary(password));
            user = (User) em.createNamedQuery("signIn").setParameter("loginUsr", login).setParameter("passUsr", HashMD5.hashText(new String(passwordBytes))).getSingleResult();
        } catch (Exception e) {
            throw new ReadException(e.getMessage());
        }

        return user;
    }

}
