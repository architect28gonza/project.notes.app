package com.note.Singleton;

import java.net.URI;

import com.note.bakingBeans.WebSocketClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Named
@ApplicationScoped
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SocketSingleton {

    private static final String URL_SOCKET = "ws://localhost:8081/websocket";
    private static SocketSingleton socketSingleton;

    WebSocketContainer container;

    public static synchronized SocketSingleton obtenersocketSingleton() {
        if (socketSingleton == null) {
            socketSingleton = new SocketSingleton();
        }
        return socketSingleton;
    }

    public Session getSessionSocket() {
        this.container = ContainerProvider.getWebSocketContainer();
        try {
            return container.connectToServer(WebSocketClient.class, URI.create(URL_SOCKET));
        } catch (Exception e) {
            return null;
        }
    }
}
