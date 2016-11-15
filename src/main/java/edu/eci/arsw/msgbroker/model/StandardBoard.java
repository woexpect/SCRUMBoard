package edu.eci.arsw.msgbroker.model;

import edu.eci.arsw.msgbroker.model.interfaces.Backlog;
import edu.eci.arsw.msgbroker.model.interfaces.Board;
import java.util.ArrayList;

/**
 * Clase tipo board que define el modelo de los boards de aplicacion
 * @author johan peña
 */
public class StandardBoard implements Board{
    
    String nombre;
    String descripcion;
    String clave;
    ArrayList<String> colaboradores = new ArrayList<>(); 
    Backlog backlogBoard;
    String correo;
    
    public StandardBoard(){
    }
    
    @Override
    public void crearBacklog(String clave){
        backlogBoard = new StandardBackLog("Backlog de proyecto " + clave, "");
    }
    
    public StandardBoard(String nombre, String descripcion, String clave){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.clave = clave;
    }
    
    public StandardBoard(String nombre, String descripcion, String clave, String correo){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.clave = clave;
        this.correo = correo;
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
    
    public void setClave(String clave){
        this.clave = clave;
    }
    
    public String getClave(){
        return clave;
    }
    
    public void setCorreo(String correo){
        this.correo = correo;
    }
    
    public String getCorreo(){
        return correo;
    }

    @Override
    public void addCollaborator(String mail) {
        colaboradores.add(mail);
    }
    
    @Override
    public ArrayList<String> getCollaborators() {
        return colaboradores;
    }

    @Override
    public void addBackLog(Backlog backlog) {
        this.backlogBoard = backlog;
    }

    @Override
    public Backlog getBackLog() {
        return backlogBoard;
    }
    
    @Override
    public String toString() {
        return "Board{" + "nombre=" + nombre + "descripcion=" + descripcion + "clave=" + clave + "}";
    }
}
