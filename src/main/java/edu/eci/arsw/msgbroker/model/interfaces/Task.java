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
public interface Task {
    
    public void addUser(User user);
    public ArrayList<User> getUsers();
    
}
