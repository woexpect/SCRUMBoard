/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker.model;

import edu.eci.arsw.msgbroker.model.interfaces.Backlog;
import edu.eci.arsw.msgbroker.model.interfaces.Sprint;
import java.util.ArrayList;

/**
 *
 * @author johan peña
 */
public class StandardBackLog implements Backlog{
    
    String nombre;
    String descripcion;
    ArrayList<Sprint> sprints = new ArrayList<>();
    
    public StandardBackLog(){
    }
    
    public StandardBackLog(String nombre, String descripcion){
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String getDescripcion(){
        return descripcion;
    }

    @Override
    public void addSprint(Sprint sprint) {
        sprints.add(sprint);
    }

    @Override
    public ArrayList<Sprint> getSprints() {
        return sprints;
    }
    
}
