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
    ArrayList<Sprint> sprints;

    @Override
    public void addSprint(Sprint sprint) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Sprint> getSprints() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
