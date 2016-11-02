/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker.model;

import edu.eci.arsw.msgbroker.model.interfaces.Task;
import edu.eci.arsw.msgbroker.model.interfaces.User;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author 2092063
 */
public class StandardTask implements Task{
    
    String nombre;
    String descripcion;
    String status;
    Date fechaInicio;
    Date fechaFin;
    ArrayList<User> usuarios;
    
}
