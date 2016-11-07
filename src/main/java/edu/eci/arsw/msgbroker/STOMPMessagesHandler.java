/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.msgbroker;

import edu.eci.arsw.msgbroker.model.interfaces.Board;
import edu.eci.arsw.msgbroker.services.InMemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 *
 * @author 2092063
 */

@Controller
public class STOMPMessagesHandler {
    
    @Autowired
    SimpMessagingTemplate msgt;
    
    @Autowired
    InMemoryPersistence imp;
    
    @MessageMapping("/board/notify")
    public void notify(Board board){
        
    }
    
//    @MessageMapping("/letter")
//    public void getLetter(Letter lt) throws GameNotFoundException{
//        System.out.println("Nueva Letra recibida en el servidor!: " + lt);
//        msgt.convertAndSend("/topic/letter."+lt.getGid(), lt);  
//        String a = gameServices.getCurrentGuessedWord(lt.getGid());
//        msgt.convertAndSend("/topic/wupdate."+lt.getGid(), a);  
//    }
//    
//    //PENDIENTE AÑADIR PETICION GET QUE TRAIGA STATUS Y WINNER
//    
//    @MessageMapping("/word")
//    public void getWord(Letter lt) throws GameNotFoundException{
//        System.out.println("Nueva palabra recibida en el servidor!: " + lt);
//        Boolean flag = gameServices.guessWord(lt.getUser(), lt.getGid(), lt.getWord());
//        System.out.println("FLAG: " + flag);
//        System.out.println("WINNNER: " + lt.getUser());
//        if(flag){
//            msgt.convertAndSend("/topic/winner."+lt.getGid(), lt.getUser());  
//            msgt.convertAndSend("/topic/wupdate."+lt.getGid(), gameServices.getWord(lt.getGid()));  
//        }
//    }
    
}
