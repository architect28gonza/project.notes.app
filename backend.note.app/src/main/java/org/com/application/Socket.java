package org.com.application;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class Socket {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket opened: " + session.getId());
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        System.out.println("MENSAJES:::");
        System.out.println("DAT : " + message);
        System.out.println("ID : " + session.getId());
        return "Hello!!";
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("WebSocket closed: " + session.getId());
    }

    @OnError
    public void onError(Throwable error) {
        System.err.println("WebSocket error: ");
        error.printStackTrace();
    }
}

