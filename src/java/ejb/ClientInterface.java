/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Client;
import entities.StatusEnum;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.List;

/**
 *
 * @author 2dam
 */
public interface ClientInterface {
    
    public void createClient(Client client) throws CreateException;
    
    public void updateClientPassword(Client client) throws UpdateException;
    
    public void updateClient(Client client) throws UpdateException;
    
    public void removeClient(Client client) throws DeleteException;
    
    public List<Client> findAllClient() throws ReadException;
    
    public Client findClientByEmail(String email) throws ReadException;
    
    public Client findClientById(Integer id) throws ReadException;
    
    public List<Client> findClientBySearch(String value) throws ReadException;
    
    public List<Client> findClientByStatus(StatusEnum status) throws ReadException;
    
    public Client findClientByLogin(String login) throws ReadException;
    
    public void recoverPassword(Client client) throws UpdateException;
    
}
