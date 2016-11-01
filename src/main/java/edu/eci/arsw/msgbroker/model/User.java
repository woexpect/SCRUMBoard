/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker.model;

/**
 *
 * @author johan
 */
public class User {
    
    String name;
    String pwd;
    String uname;
    String mail;
    int id;
    
    public User(){
    }
    
    public User(String name, String pwd, String uname, String mail, int id){
        this.name = name;
        this.pwd = pwd;
        this.uname = uname;
        this.mail = mail;
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
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
        return "User{" + "name=" + name + "pwd=" + pwd + "uname=" + uname + "mail=" + mail + "id=" + id +"}";
    }
    
}
