package edu.eci.arsw.msgbroker;

import edu.eci.arsw.msgbroker.model.StandardBoard;
import edu.eci.arsw.msgbroker.model.interfaces.Board;
import edu.eci.arsw.msgbroker.model.interfaces.User;
import edu.eci.arsw.msgbroker.services.InMemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(path = "/{clave}",method = RequestMethod.GET)
    public ResponseEntity<?> test(@PathVariable String clave){
        try{
            System.out.println("ENTRA GET --> " + clave);
            return new ResponseEntity<>(imp.getBoard(clave), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Error al agregar Usuario.",HttpStatus.FORBIDDEN);  
        }
    }
    
    @RequestMapping(path = "/userboard/{mail}",method = RequestMethod.GET)
    public ResponseEntity<?> buscarBoardsUsuario(@PathVariable String mail){
        try{
            System.out.println("ENTRA GET --> " + mail);
            return new ResponseEntity<>(imp.getBoardsUser(mail + ".com"), HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error al agregar Usuario.",HttpStatus.FORBIDDEN);  
        }
    }
    
    
    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createBoard(@RequestBody StandardBoard board){
        try{
            System.out.println("ENTRA POST --> " + board);
            String creado = imp.crearBoard(board);
            System.out.println("Board: " + creado);
            return new ResponseEntity<>(creado,HttpStatus.CREATED);                
        }catch(Exception e){
            return new ResponseEntity<>("Error al agregar el board.",HttpStatus.FORBIDDEN);  
        }
    }
    
    @RequestMapping(path = "/{clave}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> agregarColaborador(@PathVariable String clave, @RequestBody String mail){
        try{
            System.out.println("ENTRA POST --> " + mail);
            String creado = imp.agregarColaborador(mail, clave);
            return new ResponseEntity<>(creado,HttpStatus.CREATED);                
        }catch(Exception e){
            return new ResponseEntity<>("Error al agregar Usuario.",HttpStatus.FORBIDDEN);  
        }
    }
    
}
