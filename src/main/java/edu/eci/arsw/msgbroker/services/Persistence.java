/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker.services;

import edu.eci.arsw.msgbroker.model.interfaces.Board;
import edu.eci.arsw.msgbroker.model.interfaces.Sprint;
import edu.eci.arsw.msgbroker.model.interfaces.Task;
import edu.eci.arsw.msgbroker.model.interfaces.User;
import java.util.ArrayList;

/**
 *
 * @author alejandro
 */
public interface Persistence {
    
    public void preloadUsers();
    
    public User getUser(String mail);
    
    public User login(String mail, String pwd);
    
    public String register(User user);
    
    public void preloadBoards();
    
    public Board getBoard(String clave);
    
    public void addBackLog(String nombre, String descripcion, String clave);
    
    public void addSprint(String nombre, String descripcion, String status, String clave);
    
    public void addTask(String nombre, String descripcion, String status, String clave, int sp);
    
    public void addColaborador(String mail, String clave);
    
    public String agregarColaborador(String mail, String clave);
    
    public ArrayList<Board> getBoardsUser(String mail);
    
    public String crearBoard(Board board);
    
    public String agregarSprint(String clave, Sprint sprint);
    
    public String agregarTarea(String clave, String id, Task task);
      
    
    
}
