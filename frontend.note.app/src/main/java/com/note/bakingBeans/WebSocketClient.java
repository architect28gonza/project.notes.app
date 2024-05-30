package com.note.bakingBeans;

import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;

@ClientEndpoint
public class WebSocketClient {

    @OnClose
    public void onClose(Session session) {
        System.out.println("Conexión cerrada.");
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Mensaje recibido: " + message);
    }

    @OnError
    public void onError(Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
    }
}
