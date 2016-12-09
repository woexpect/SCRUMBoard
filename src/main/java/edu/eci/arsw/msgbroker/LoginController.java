package edu.eci.arsw.msgbroker;

import edu.eci.arsw.msgbroker.model.interfaces.User;
import edu.eci.arsw.msgbroker.services.InMemoryPersistence;
import edu.eci.arsw.msgbroker.services.REDISPersistence;
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
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    REDISPersistence imp;

    @RequestMapping(path = "/user/{mail}",method = RequestMethod.GET)
    public ResponseEntity<?> test(@PathVariable String mail){
        try{
            System.out.println("ENTRA GET --> " + mail);
            return new ResponseEntity<>(imp.getUser(mail + ".com"), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Error al cargar Usuario.",HttpStatus.FORBIDDEN);  
        }
    }
    
    /**
     * Se recibe mediante POST el correo y contraseña que el usuario a ingresado
     * en el cliente para hacer login.
     * @param user Se crea instancia de objeto User para recibir correo y contraseña.
     * @return ResponseEntity con User si autenticación OK o null de lo contrario
     */
    @RequestMapping(path = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> doLogin(@RequestBody User user){
        try{
            System.out.println("ENTRA POST --> " + user);
            User res = imp.login(user.getMail(), user.getPwd());
            System.out.println("RES: " + res);
            return new ResponseEntity<>(res,HttpStatus.CREATED);                
        }catch(Exception e){
            return new ResponseEntity<>("Error al agregar Usuario.",HttpStatus.FORBIDDEN);  
        }
    }
}
