/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker.model;

import edu.eci.arsw.msgbroker.model.interfaces.Sprint;
import edu.eci.arsw.msgbroker.model.interfaces.Task;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author 2092063
 */
public class StandardSprint implements Sprint{
    
    String nombre;
    String descripcion;
    String status;
    Date fechaInicio;
    Date fechaFin;
    ArrayList<Task> tareas;

    @Override
    public void addTask(Task task) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Task> getTasks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
