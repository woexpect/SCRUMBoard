package edu.eci.arsw.msgbroker.model.interfaces;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Clase de tipo User para el manejo de usuarios dentro de la aplicación.
 * @author johan peña
 */
public class User {
    
    String name;
    String pwd;
    String uname;
    String mail;
    
    /**
     * Constructor por defecto
     */
    public User(){
    }
    
    /**
     * Constructor de User por parametros
     * @param name      nombre del usuario
     * @param pwd       contrasena del usuario
     * @param uname     username del usuario
     * @param mail      correo del usuario
     */
    public User(String name, String pwd, String uname, String mail){
        this.name = name;
        this.pwd = pwd;
        this.uname = uname;
        this.mail = mail;
    }
    
    public String getMail(){
        return mail;
    }
    
    public void setMail(String mail){
        this.mail = mail;
    }
    
    public String getUserName(){
        return uname;
    }
    
    public void setUserName(String uname){
        this.uname = uname;
    }
    
    public String getPwd(){
        return pwd;
    }
    
    public void setPwd(String pwd){
        this.pwd = pwd;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "User{" + "name=" + name + "pwd=" + pwd + "uname=" + uname + "mail=" + mail + "}";
    }
    
}
