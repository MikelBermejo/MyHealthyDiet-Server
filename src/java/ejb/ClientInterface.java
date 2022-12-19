/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Client;
import entities.StatusEnum;
import java.util.List;

/**
 *
 * @author 2dam
 */
public interface ClientInterface {
    
    public void createClient(Client client);
    
    public void updateClient(Client client);
    
    public void removeClient(Client client);
    
    public List<Client> findAllClient();
    
    public Client findClientById(Integer id);
    
    public List<Client> findClientBySearch(String value);
    
    public List<Client> findClientByStatus(StatusEnum status);
    
}
