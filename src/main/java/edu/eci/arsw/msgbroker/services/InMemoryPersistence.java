/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker.services;
import edu.eci.arsw.msgbroker.model.StandardBackLog;
import edu.eci.arsw.msgbroker.model.StandardBoard;
import edu.eci.arsw.msgbroker.model.StandardSprint;
import edu.eci.arsw.msgbroker.model.StandardTask;
import edu.eci.arsw.msgbroker.model.interfaces.Backlog;
import edu.eci.arsw.msgbroker.model.interfaces.Board;
import edu.eci.arsw.msgbroker.model.interfaces.Sprint;
import edu.eci.arsw.msgbroker.model.interfaces.Task;
import edu.eci.arsw.msgbroker.model.interfaces.User;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

/**
 * Persistencia en memoria de la aplicación, contiene usuarios, boards y demás
 * elementos necesarios para la ejecución de la aplicación.
 * @author johan peña
 */
@Service
public class InMemoryPersistence{
    
     ConcurrentHashMap<String, User> users;
     ConcurrentHashMap<String, Board> boards;
    
    
    public InMemoryPersistence(){
        users = new ConcurrentHashMap<>();
        boards = new ConcurrentHashMap<>();
        preloadUsers();
        preloadBoards();
    }

    private void preloadUsers() {
        User a = new User("johan penia", "asd123", "woexpect", "sebasp95@hotmail.com");
        User b = new User("georgios ikosidekas", "qwe123", "giko", "gikosidekas@hotmail.com");
        User c = new User("alejandro ramirez", "12345", "amirez", "alejormrz@hotmail.com");
        users.put(a.getMail(), a);
        users.put(b.getMail(), b);
        users.put(c.getMail(), c);
        users.get("sebasp95@hotmail.com").addClaveBoard("ARSQ20165");
        users.get("sebasp95@hotmail.com").addClaveBoard("PSIS30");
        users.get("alejormrz@hotmail.com").addClaveBoard("COSW24");
    }
    
    public User getUser(String mail){
        return users.get(mail);
    }

    public User login(String mail, String pwd) {
        User res = null;
        res = users.get(mail);
        if(res != null){
            if(res.getPwd().equals(pwd)){
                return res;
            }else{
                return null;
            }
        }
        return null;
    }

    public String register(User user) {
        String res = "";
        User u = null;
        u = users.get(user.getMail());
        if(u != null){
            res = "El usuario con el correo descrito ya existe.";
        }else{
            users.put(user.getMail(), user);
            res = "Has sido agregado a la plataforma satisfactoriamente.";
        }
        return res;
    }

    
    //====================================================================================================================================================
    
    private void preloadBoards() {
        Board a = new StandardBoard("PROYECTO ARSW", "Este board contiene la información sobre el proyecto de arsw.","ARSQ20165");
        Board b = new StandardBoard("PROYECTO PSIS", "Este board contiene la información sobre el proyecto de PSIS.","PSIS30");
        Board c = new StandardBoard("PROYECTO COSW", "Este board contiene la información sobre el proyecto de COSW.","COSW24");
        boards.put(a.getClave(), a);
        boards.put(b.getClave(), b);
        boards.put(c.getClave(), c);
        addColaborador("sebasp95@hotmail.com1", "ARSQ20165");
        addColaborador("sebasp95@hotmail.com1", "PSIS30");
        addBackLog("BackLog ARSW", "Backlog para el proyecto ARSW", "ARSQ20165");
        addSprint("Sprint 1 ARSW", "Sprint 1 para el proyecto ARSW", "En curso" ,"ARSQ20165");
        addSprint("Sprint 2 ARSW", "Sprint 2 para el proyecto ARSW", "Terminado" ,"ARSQ20165");
        addTask("Task 1 ARSW", "Tarea 1 para el Sprint 1 ARSW", "En Curso" ,"ARSQ20165", 0);
        addTask("Task 2 ARSW", "Tarea 2 para el Sprint 1 ARSW", "En Curso" ,"ARSQ20165", 0);
        addTask("Task 1 ARSW", "Tarea 1 para el Sprint 2 ARSW", "Terminado" ,"ARSQ20165", 1);
    }
    
    public Board getBoard(String clave){
        return boards.get(clave);
    }
    
    public void addBackLog(String nombre, String descripcion, String clave){
        Backlog b = new StandardBackLog(nombre, descripcion);
        boards.get(clave).addBackLog(b);
    }
    
    public void addSprint(String nombre, String descripcion, String status, String clave){
        Sprint s = new StandardSprint(nombre, descripcion, status);
        boards.get(clave).getBackLog().addSprint(s);
    }
    
    public void addTask(String nombre, String descripcion, String status, String clave, int sp){
        Task t = new StandardTask(nombre, descripcion, status);
        boards.get(clave).getBackLog().getSprints().get(sp).addTask(t);
    }
    
    public void addColaborador(String mail, String clave){
        boards.get(clave).addCollaborator(mail);
    }
    
    public String agregarColaborador(String mail, String clave){
        String res = "";
        ArrayList a = boards.get(clave).getCollaborators();
        if(a.contains(mail)){
            res = "El colaborador descrito ya está agregado en el board.";
        }else{
            res = "Colaborador agregado satisfactoriamente.";
            users.get(mail.substring(0, mail.length()-1)).addClaveBoard(clave);
            boards.get(clave).addCollaborator(mail);            
        }
        return res;
    }
    
    /**
     * Busca los boards para el usuario seleccionado
     * @param mail
     * @return 
     */
    public ArrayList<Board> getBoardsUser(String mail){
        ArrayList<String> c = users.get(mail).getClaves();
        ArrayList<Board> res = new ArrayList<>();
        for (int i = 0; i < c.size(); i++) {
            if(boards.get(c.get(i)) != null){
                res.add(boards.get(c.get(i)));
            }
        }
        return res;
    }

    public String crearBoard(Board board) {
        String res = "";
        Board b = boards.get(board.getClave());
        if(b != null){
            res = "El board con la clave: " + board.getClave() + " ya existe, por favor ingrese otra clave.";
        }else{
            res = "Se ha registrado el board correctamente.";
            boards.put(board.getClave(), board);
            boards.get(board.getClave()).crearBacklog(board.getClave());
        }
        return res;
    }
}
