package edu.eci.arsw.msgbroker.model;

import edu.eci.arsw.msgbroker.model.interfaces.Backlog;
import edu.eci.arsw.msgbroker.model.interfaces.Board;
import edu.eci.arsw.msgbroker.model.interfaces.User;
import java.util.ArrayList;

/**
 * Clase tipo board que define el modelo de los boards de aplicacion
 * @author johan peña
 */
public class StandardBoard implements Board{
    
    String nombre;
    String descripcion;
    ArrayList<User> colaboradores;
    Backlog backlogBoard;
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public StandardBoard(){
    }
    
    public StandardBoard(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    @Override
    public void addCollaborator(User user) {
        colaboradores.add(user);
    }
    
    @Override
    public ArrayList<User> getCollaborators() {
        return colaboradores;
    }

    @Override
    public void addBackLog(Backlog backlog) {
        this.backlogBoard = backlog;
    }

    @Override
    public Backlog getBackLogs() {
        return backlogBoard;
    }
}
