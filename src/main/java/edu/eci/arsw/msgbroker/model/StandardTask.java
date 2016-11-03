/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker.model;

import edu.eci.arsw.msgbroker.model.interfaces.Task;
import edu.eci.arsw.msgbroker.model.interfaces.User;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author 2092063
 */
public class StandardTask implements Task{
    
    String nombre;
    String descripcion;
    String status;
    Date fechaInicio;
    Date fechaFin;
    ArrayList<User> usuarios = new ArrayList<>();
    
    public StandardTask(){    
    }
    
    public StandardTask(String nombre, String descripcion, String status){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.status = status;
    }
    
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
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public String getStatus(){
        return status;
    }

    @Override
    public void addUser(User user) {
        usuarios.add(user);
    }

    @Override
    public ArrayList<User> getUsers() {
        return usuarios;
    }
    
}
