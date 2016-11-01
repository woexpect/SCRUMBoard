/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker.services;
import edu.eci.arsw.msgbroker.model.User;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/**
 *
 * @author johan
 */
@Service
public class InMemoryPersistence{
    
    private final ConcurrentHashMap<Integer, User> users;
    
    public InMemoryPersistence(){
        users = new ConcurrentHashMap<>();
        preloadUsers();
    }

    public boolean createUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User getUser(String pwd, int id, String userName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void preloadUsers() {
        User a = new User("johan penia", "asd123", "woexpect", "sebasp95@hotmail.com", 100000);
        User b = new User("georgios ikosidekas", "qwe123", "giko", "gikosidekas@hotmail.com", 100001);
        User c = new User("alejandro ramirez", "12345", "amirez", "alejormrz@hotmail.com", 100002);
        users.put(a.getId(), a);
        users.put(b.getId(), b);
        users.put(c.getId(), c);
    }
    
    public User getUser(int id){
        return users.get(id);
    }
    
}
