/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker.model.interfaces;

import java.util.ArrayList;

/**
 *
 * @author johan
 */
public interface Board {
    
    public void addCollaborator(String mail);
    public ArrayList<String> getCollaborators();
    public void addBackLog(Backlog backlog);
    public String getNombre();
    public String getDescripcion();
    public String getClave();
    public Backlog getBackLog();
    public void crearBacklog(String clave);
    
}
