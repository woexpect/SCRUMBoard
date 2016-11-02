/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker.model.interfaces;

import java.util.ArrayList;

/**
 * Clase que define el modelo para los Backlogs de la aplicación
 * @author johan peña
 */
public interface Backlog {
    
    public void addSprint(Sprint sprint);
    public ArrayList<Sprint> getSprints();
    
}
