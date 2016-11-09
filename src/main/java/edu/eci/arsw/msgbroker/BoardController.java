package edu.eci.arsw.msgbroker;

import edu.eci.arsw.msgbroker.model.StandardBoard;
import edu.eci.arsw.msgbroker.model.StandardSprint;
import edu.eci.arsw.msgbroker.model.interfaces.Board;
import edu.eci.arsw.msgbroker.services.InMemoryPersistence;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Este controlador se encarga de recibir las peticiones referentes al logeo de la aplicacion.
 * @author johan peña
 */
@RestController
@RequestMapping("/board")
public class BoardController {
    
    @Autowired
    InMemoryPersistence imp;
    
    @Autowired
    SimpMessagingTemplate msgt;    
    

    @RequestMapping(path = "/{clave}",method = RequestMethod.GET)
    public ResponseEntity<?> test(@PathVariable String clave){
        try{
            System.out.println("ENTRA GET --> " + clave);
            return new ResponseEntity<>(imp.getBoard(clave), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Error al agregar Usuario.",HttpStatus.FORBIDDEN);  
        }
    }
    
    /**
     * Este controlador se encarga de entregar los boards del usuario con el email dado.
     * @param mail Correo del usuario del cual se solicitan sus boards.
     * @return ArrayList con los boards a los cuales el usuario está suscrito.
     */
    @RequestMapping(path = "/userboard/{mail}",method = RequestMethod.GET)
    public ResponseEntity<?> buscarBoardsUsuario(@PathVariable String mail){
        try{
            System.out.println("------------------Start[[buscarBoardsUsuario]]");
            System.out.println("mail: " + mail + ".com");
            System.out.println("------------------End[[buscarBoardsUsuario]]");
            return new ResponseEntity<>(imp.getBoardsUser(mail + ".com"), HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error al obtener boards de usuario.",HttpStatus.FORBIDDEN);  
        }
    }
    
    /**
     * Controlador que se encarga de agregar boards a la persistencia del proyecto.
     * @param board Objeto JSON con la información del board a crear.
     * @return String con respuesta de la transacción.
     */
    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createBoard(@RequestBody StandardBoard board){
        try{
            System.out.println("------------------Start[[createBoard]]");
            System.out.println("ENTRA POST --> " + board);
            String creado = imp.crearBoard(board);
            System.out.println("Board: " + creado);
            System.out.println("------------------End[[createBoard]]");
            return new ResponseEntity<>(creado,HttpStatus.CREATED);                
        }catch(Exception e){
            return new ResponseEntity<>("Error al agregar el board.",HttpStatus.FORBIDDEN);  
        }
    }
    
    /**
     * Controlador que se encarga de agregar colaboradores a los boards dada su clave y mail de colaborador.
     * @param clave
     * @param mail
     * @return 
     */
    @RequestMapping(path = "/{clave}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> agregarColaborador(@PathVariable String clave, @RequestBody String mail){
        try{
            System.out.println("------------------Start[[agregarColaborador]]");
            System.out.println("ENTRA POST --> " + mail);
            String creado = imp.agregarColaborador(mail, clave);
            System.out.println("------------------End[[agregarColaborador]]");
            return new ResponseEntity<>(creado,HttpStatus.CREATED);                
        }catch(Exception e){
            return new ResponseEntity<>("Error al agregar Usuario.",HttpStatus.FORBIDDEN);  
        }
    }
    
    @RequestMapping(path = "/sprint/{clave}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> agregarSprint(@PathVariable String clave, @RequestBody StandardSprint sprint){
        try{
            System.out.println("------------------Start[[agregarColaborador]]");
            System.out.println("ENTRA POST --> " + sprint);
            String creado = imp.agregarSprint(clave, sprint);
            System.out.println("------------------End[[agregarColaborador]]");
            return new ResponseEntity<>(creado,HttpStatus.CREATED);                
        }catch(Exception e){
            return new ResponseEntity<>("Error al agregar Usuario.",HttpStatus.FORBIDDEN);  
        }
    }
    
}
