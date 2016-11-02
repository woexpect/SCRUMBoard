/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker.services;
import edu.eci.arsw.msgbroker.model.interfaces.Backlog;
import edu.eci.arsw.msgbroker.model.interfaces.Board;
import edu.eci.arsw.msgbroker.model.interfaces.Sprint;
import edu.eci.arsw.msgbroker.model.interfaces.Task;
import edu.eci.arsw.msgbroker.model.interfaces.User;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/**
 * Persistencia en memoria de la aplicación, contiene usuarios, boards y demás
 * elementos necesarios para la ejecución de la aplicación.
 * @author johan peña
 */
@Service
public class InMemoryPersistence{
    
     ConcurrentHashMap<String, User> users;
     ConcurrentHashMap<String, Board> boards;
     ConcurrentHashMap<String, Backlog> backlogs;
     ConcurrentHashMap<String, Sprint> sprints;
     ConcurrentHashMap<String, Task> tasks;
    
    
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
        User a = new User("johan penia", "asd123", "woexpect", "sebasp95@hotmail.com");
        User b = new User("georgios ikosidekas", "qwe123", "giko", "gikosidekas@hotmail.com");
        User c = new User("alejandro ramirez", "12345", "amirez", "alejormrz@hotmail.com");
        users.put(a.getMail(), a);
        users.put(b.getMail(), b);
        users.put(c.getMail(), c);
    }
    
    public User getUser(String mail){
        return users.get(mail);
    }

    public User login(String mail, String pwd) {
        User res = null;
        res = users.get(mail);
        if(res != null){
            if(res.getPwd().equals(pwd)){
                return res;
            }else{
                return null;
            }
        }
        return null;
    }

    public String register(User user) {
        String res = "";
        User u = null;
        u = users.get(user.getMail());
        if(u != null){
            res = "El usuario con el correo descrito ya existe.";
        }else{
            users.put(user.getMail(), user);
            res = "Has sido agregado a la plataforma satisfactoriamente.";
        }
        return res;
    }
    
}
