/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker.services;

import edu.eci.arsw.msgbroker.model.LoginInterface;
import edu.eci.arsw.msgbroker.model.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author johan
 */
@Service
public class StandardLogin implements LoginInterface{

    @Override
    public User login(User user) {
        return null;
    }
    
}
