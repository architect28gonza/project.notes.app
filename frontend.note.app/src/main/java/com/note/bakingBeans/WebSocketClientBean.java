package com.note.bakingBeans;

import java.io.Serializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.note.Singleton.SocketSingleton;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.ManagedProperty;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.websocket.Session;
import lombok.Getter;
import lombok.Setter;

@Named(value = "bbeanSocket")
@ViewScoped
public class WebSocketClientBean implements Serializable {
    
    private static final Logger LOG = LoggerFactory.getLogger(WebSocketClientBean.class);

    @Getter
    @Setter
    private String message;

    private Session session;
    
    @ManagedProperty(value="#{SocketSingleton}")
    private SocketSingleton socketSingleton;

    @PostConstruct
    public void init() {
        this.socketSingleton = SocketSingleton.obtenersocketSingleton();
        this.session = this.socketSingleton.getSessionSocket();
    }

    public void sendMessageWebSocket() {
        try {
            session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            LOG.error("Ocurrio un error : Method : sendMessageWebSocket", e);
        }
    }    
}
