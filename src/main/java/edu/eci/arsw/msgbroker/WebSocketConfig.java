package edu.eci.arsw.msgbroker;

/**
 *
 * @author johan peña
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        
        config.enableStompBrokerRelay("/topic/").setRelayHost("black-boar.rmq.cloudamqp.com").setRelayPort(61613).
                setClientLogin("kpbdtdlb").
                setClientPasscode("kPzV9zglJZfIM87s0bzzwaXQbvJM0HhD").
                setSystemLogin("kpbdtdlb").
                setSystemPasscode("kPzV9zglJZfIM87s0bzzwaXQbvJM0HhD").
                setVirtualHost("kpbdtdlb");
                
        config.setApplicationDestinationPrefixes("/app");
    }    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stompendpoint").setAllowedOrigins("*").withSockJS();

    }

}
