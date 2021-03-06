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
import edu.eci.arsw.msgbroker.util.JedisUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 *
 * @author alejandro
 */
@Service
public final class REDISPersistence implements Persistence{
    

    
    public REDISPersistence(){

    }

    @Override
    public void preloadUsers() {
        /*
        User a = getUser("sebasp95@hotmail.com");
        User b = getUser("gikosidekas@hotmail.com");
        User c = getUser("alejormrz@hotmail.com");
        
        users.put(a.getMail(), a);
        users.put(b.getMail(), b);
        users.put(c.getMail(), c);
        users.get("sebasp95@hotmail.com").addClaveBoard("ARSQ20165");
        users.get("sebasp95@hotmail.com").addClaveBoard("PSIS30");
        users.get("alejormrz@hotmail.com").addClaveBoard("COSW24");*/
    
    }

    @Override
    public User getUser(String mail) {
        Jedis jedis = JedisUtil.getPool().getResource();
        Map<String, String> properties = jedis.hgetAll("user:" + mail);
        User usuario = new User(properties.get("name"), properties.get("pwd"), properties.get("uname"), properties.get("email"));
        jedis.close();
        return usuario;
        }

    @Override
    public User login(String mail, String pwd) {
        User res = getUser(mail);
        if(res != null){
            if(res.getPwd().equals(pwd)){
                return res;
            }else{
                return null;
            }
        }
        return null;
    }

    @Override
    public String register(User user) {
        String res = "";
        
        User u  = getUser(user.getMail());
        if(u.getMail() != null){
            res = "El usuario con el correo descrito ya existe.";
        }else{
            Map<String, String> userProperties = new HashMap<String, String>();
            userProperties.put("name", user.getName());
            userProperties.put("pwd", user.getPwd());
            userProperties.put("uname", user.getUserName());
            userProperties.put("email", user.getMail());
            
            Jedis jedis = JedisUtil.getPool().getResource();
            jedis.hmset("user:" + user.getMail(), userProperties);
            jedis.close();
            res = "Has sido agregado a la plataforma satisfactoriamente.";
        }
        return res;
       
    }

    @Override
    public void preloadBoards() {
        /*
        Board a = getBoard("ARSQ20165");
        Board b = getBoard("PSIS30");
        Board c = getBoard("COSW24");
        System.out.println(a);
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
        */
    }

    @Override
    public Board getBoard(String clave) {
        Jedis jedis = JedisUtil.getPool().getResource();
        Map<String, String> properties = jedis.hgetAll("board:" + clave);
        StandardBoard board = new StandardBoard(properties.get("boardName"), properties.get("boardDescription"), properties.get("pass"));      
        
        
        
        for(int i=0; i< (int) (long) jedis.llen("collaborators_" + clave); i++){
            String col = jedis.lindex("collaborators_" + clave, i);
            board.addCollaborator(col);
        }
        
        board.addBackLog(getBacklog(clave, jedis));
        
        ArrayList<Sprint> sprints = getSprints(properties.get("boardName"), clave, jedis);
        
        for(int i=0; i<sprints.size(); i++){
            board.getBackLog().addSprint(sprints.get(i));
        }
     
        jedis.close();

        return board;
    }
    
    private Backlog getBacklog(String clave, Jedis jedis){
        Map<String, String> backlogProperties = jedis.hgetAll("backlog:" + clave);
        return new StandardBackLog(backlogProperties.get("backlogName"), backlogProperties.get("backlogDescription"));
    }
    
    private ArrayList<Sprint> getSprints(String nombreBoard, String clave, Jedis jedis){
        ArrayList<Sprint> sprints = new ArrayList<>();
        for(int i=0; i<jedis.llen("sprints_" + clave); i++){
            Map<String, String> sprintProperties = jedis.hgetAll("sprint:" + clave + "_" + jedis.lindex("sprints_" + clave, i));
            StandardSprint sp = new StandardSprint(sprintProperties.get("sprintName"), sprintProperties.get("sprintDescription"), sprintProperties.get("sprintStatus"));
            ArrayList<Task> tasks = getTasks(nombreBoard, clave, sprintProperties.get("sprintName"), jedis);
            
            for (Task task : tasks) {
                sp.addTask(task);
            }
            sprints.add(sp);
        }
        return sprints;
    }
    
    private ArrayList<Task> getTasks(String nombreBoard, String clave, String nombreSprint, Jedis jedis){
        ArrayList<Task> tasks = new ArrayList<>();
        for(int i=0; i<jedis.llen(clave + "_" + nombreSprint + "_Tasks"); i++){
            Map<String, String> taskProperties = jedis.hgetAll("task:" + clave 
                    + "_" 
                    + nombreSprint 
                    + "_" 
                    + jedis.lindex(clave + "_" + nombreSprint + "_Tasks", i));
            StandardTask task = new StandardTask(taskProperties.get("taskName"), taskProperties.get("taskDescription"), taskProperties.get("taskStatus"));
            tasks.add(task);
        }
        return tasks;
    }

    @Override
    public void addBackLog(String nombre, String descripcion, String clave) {       
        Map<String, String> backlogProperties = new HashMap<String, String>();
        backlogProperties.put("backlogName", nombre);
        backlogProperties.put("backlogDescription", descripcion);
        Jedis jedis = JedisUtil.getPool().getResource();
        jedis.hmset("backlog:" + clave, backlogProperties);
        jedis.close();
        
        Backlog b = new StandardBackLog(nombre, descripcion);

    }

    @Override
    public void addSprint(String nombre, String descripcion, String status, String clave) {
        Map<String, String> sprintProperties = new HashMap<String, String>();
        sprintProperties.put("sprintName", nombre);
        sprintProperties.put("sprintDescription", descripcion);
        sprintProperties.put("sprintStatus", status);
        sprintProperties.put("initDate", "");
        sprintProperties.put("endDate", "");
        Jedis jedis = JedisUtil.getPool().getResource();
        jedis.hmset("sprint:" + clave + "_" + nombre, sprintProperties);
        jedis.lpush("sprints_" + clave , nombre);
        jedis.close();
    }

    @Override
    public void addTask(String nombre, String descripcion, String status, String clave, int sp) {
        Map<String, String> taskProperties = new HashMap<String, String>();
        taskProperties.put("taskName", nombre);
        taskProperties.put("taskDescription", descripcion);
        taskProperties.put("taskStatus", status);
        taskProperties.put("initDate", "");
        taskProperties.put("endDate", "");
        Jedis jedis = JedisUtil.getPool().getResource();
        jedis.hmset("task:" + clave + "_" + Integer.toString(sp) + "_" + nombre, taskProperties);
        jedis.close();    
    }

    @Override
    public void addColaborador(String mail, String clave) {

        Jedis jedis = JedisUtil.getPool().getResource();
        jedis.lpush("collaborators_" + clave, mail);
        jedis.close();
    }

    @Override
    public String agregarColaborador(String mail, String clave) {
        String res = "";
        
        ArrayList a = getBoard(clave).getCollaborators();
        if(a.contains(mail)){
            res = "El colaborador descrito ya está agregado en el board.";
        }else{
            res = "Colaborador agregado satisfactoriamente.";
            //users.get(mail).addClaveBoard(clave);
            //boards.get(clave).addCollaborator(mail);
            
            System.out.println("entro a agregarColaborador");
            Jedis jedis = JedisUtil.getPool().getResource();
            jedis.lpush("collaborators_" + clave, mail);
            jedis.lpush("claves_" + mail, clave);
            jedis.close();
        }
        return res;
    }

    @Override
    public ArrayList<Board> getBoardsUser(String mail) {
        ArrayList<String> c = new ArrayList<>();
        ArrayList<Board> res = new ArrayList<>();        
        Jedis jedis = JedisUtil.getPool().getResource();
        for(int i=0; i<jedis.llen("claves_" + mail); i++){
            c.add(jedis.lindex("claves_" + mail, i));
        }
        
        for (int i = 0; i < c.size(); i++) {
            if(getBoard(c.get(i)) != null){
                System.out.println("board encontrado:"+getBoard(c.get(i)));
                res.add(getBoard(c.get(i)));
            }
        }
        
        jedis.close();
        System.out.println("resultado de boards:" +res.size());
        return res;
    }

    @Override
    public String crearBoard(Board board) {
        String res = "";
        
        Board b = getBoard(board.getClave());
        if(b.getClave() != board.getClave()){
            res = "Se ha registrado el board correctamente.";

            Map<String, String> boardProperties = new HashMap<String, String>();
            boardProperties.put("boardName", board.getNombre());
            boardProperties.put("boardDescription", board.getDescripcion());
            boardProperties.put("pass", board.getClave());
            Jedis jedis = JedisUtil.getPool().getResource();
            jedis.hmset("board:" + board.getClave(), boardProperties);
            System.out.println("colaboradores:"+board.getCollaborators().size());
            System.out.println("clave board:" + board.getClave());
            for(int i=0; i< board.getCollaborators().size(); i++){
                jedis.lpush("collaborators_" + board.getClave(), board.getCollaborators().get(i));
                String col = board.getCollaborators().get(i).substring(0, board.getCollaborators().get(i).length()-1);
                jedis.lpush("claves_" + col, board.getClave());
            }
            Map<String, String> backlogProperties = new HashMap<String, String>();
            backlogProperties.put("backlogName", board.getBackLog().getNombre());
            backlogProperties.put("backlogDescription", board.getBackLog().getDescripcion());
            jedis.hmset("backlog:" + board.getClave(), backlogProperties);
            
            jedis.close();
        }else{
            res = "El board con la clave: " + board.getClave() + " ya existe, por favor ingrese otra clave.";
        }
        return res;
    }

    @Override
    public String agregarSprint(String clave, Sprint sprint) {
        String res = "Fallo al agregar el Sprint.";
        
      
        Map<String, String> sprintProperties = new HashMap<String, String>();
        sprintProperties.put("sprintName", sprint.getNombre());
        sprintProperties.put("sprintDescription", sprint.getDescripcion());
        sprintProperties.put("sprintStatus", sprint.getStatus());
        sprintProperties.put("initDate", "");
        sprintProperties.put("endDate", "");
        Jedis jedis = JedisUtil.getPool().getResource();
        jedis.hmset("sprint:" + clave + "_" + sprint.getNombre(), sprintProperties);
        jedis.lpush("sprints_" + clave , sprint.getNombre());
        jedis.close();
        res = "Sprint agregado satisfactoriamente.";
        System.out.println(res+sprint.getNombre());
        return res;
    }

    @Override
    public String agregarTarea(String clave, String sprintName, Task task) {
            
        Map<String, String> taskProperties = new HashMap<String, String>();
        taskProperties.put("taskName", task.getNombre());
        taskProperties.put("taskDescription", task.getDescripcion());
        taskProperties.put("taskStatus", task.getStatus());
        taskProperties.put("initDate", "");
        taskProperties.put("endDate", "");
        Jedis jedis = JedisUtil.getPool().getResource();
        jedis.hmset("task:" + clave + "_" + sprintName + "_" + task.getNombre(), taskProperties);
        jedis.lpush(clave + "_" + sprintName + "_Tasks", task.getNombre());
        jedis.close();  
        String res = "Tarea agregada satisfactoriamente.";
      
        return res;
    }
    
}
