/*
 * Copyright (C) 2016 Pivotal Software, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.arsw.msgbroker;

import edu.eci.arsw.msgbroker.model.User;
import edu.eci.arsw.msgbroker.services.InMemoryPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.server.PathParam;
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
 *
 * @author hcadavid
 */
@RestController
@RequestMapping("/login")
public class LoginResourceController {
    
    @Autowired
    InMemoryPersistence imp;
    
    @RequestMapping(path = "/user/{mail}",method = RequestMethod.GET)
    public ResponseEntity<?> test(@PathVariable String mail){
        try{
            System.out.println("ENTRA GET --> " + mail);
            return new ResponseEntity<>(imp.getUser(mail + ".com"), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("Error al agregar Usuario.",HttpStatus.FORBIDDEN);  
        }
    }
    
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
    
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getCurrentWord(){
        try {    
            return new ResponseEntity<>("",HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.NOT_FOUND);
        }
    }
    

    
    
    
}
