/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker;

import edu.eci.arsw.msgbroker.model.StandardBoard;
import edu.eci.arsw.msgbroker.model.interfaces.Board;
import edu.eci.arsw.msgbroker.services.InMemoryPersistence;
import edu.eci.arsw.msgbroker.services.REDISPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author 2092063
 */

@Controller
public class STOMPMessagesHandler {
    
    @Autowired
    SimpMessagingTemplate msgt;
    
    @Autowired
    REDISPersistence imp;
    
    @MessageMapping("/board/notify")
    public void notify(Board board){
        
    }

    public void getBoard(StandardBoard board) throws Exception {
        System.out.println("Nuevo mail recibido por el servidor:"+board.getCorreo());
        System.out.println("Nuevo Board recibido por el servidor:"+board);
        msgt.convertAndSend("/topic/userboard/"+board.getCorreo(), board);
    }
    
}
