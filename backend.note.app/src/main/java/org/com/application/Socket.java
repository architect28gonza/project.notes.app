package org.com.application;

import java.util.HashMap;
import java.util.Map;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class Socket {

    private static final Map<String, Session> sessions = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        sessions.put(session.getId(), session);
        System.out.println("WebSocket opened: " + session.getId());
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        if (message.equals("INIT")) {
            System.out.println("INICIADO");
            return session.getId();
        } else {
            System.out.println("Entrada!!");
            return "Procesando...";
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session.getId());
        System.out.println("WebSocket closed: " + session.getId());
    }

    @OnError
    public void onError(Throwable error) {
        System.err.println("WebSocket error: ");
        error.printStackTrace();
    }

    // Método para obtener una sesión WebSocket por su ID
    public Session getSessionById(String sessionId) {
        return sessions.get(sessionId);
    }
}
