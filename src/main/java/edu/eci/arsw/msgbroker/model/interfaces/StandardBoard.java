package edu.eci.arsw.msgbroker.model.interfaces;

import java.util.ArrayList;

/**
 * Clase tipo board que define el modelo de los boards de aplicacion
 * @author johan peña
 */
public class StandardBoard implements Board{
    
    String nombre;
    String descripcion;
    ArrayList<User> colaboradores;

    @Override
    public void addCollaborator(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<User> getCollaborators() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addBackLog(Backlog backlog) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Backlog> getBackLogs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
    
}
