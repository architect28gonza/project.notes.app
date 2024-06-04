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
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        return (message.equals("INIT")) ? session.getId() : "Procesando...";
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session.getId());
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

    public Session getSessionById(String sessionId) {
        return sessions.get(sessionId);
    }
}
