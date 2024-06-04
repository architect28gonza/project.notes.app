package com.note.bakingBeans;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.dto.StudentInfoDto;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientSocket {

    private static final Logger LOG = Logger.getLogger(ClientSocket.class.getName());

    private static final String URL_SOCKET = "ws://localhost:8081/websocket";

    private String socketId;

    protected List<StudentInfoDto> lstStudentInfo = new ArrayList<>();

    private Session session;

    private WebSocketContainer container;

    private Supplier<WebSocketContainer> containerSupplier;

    protected boolean isSessionOpen() {
        return (this.getSession() != null && this.getSession().isOpen());
    }

    protected boolean isSessionNotNULL() {
        return (this.getSession() != null);
    }

    protected Optional<Session> getSessionServerSocket() {
        try {
            return Optional.ofNullable(this.getContainer()
                    .connectToServer(
                            GenerateXlsx.class, URI.create(URL_SOCKET)));
        } catch (Exception e) {
            LOG.severe("Error connecting to WebSocket: " + e.getMessage());
            return Optional.empty();
        }
    }

    protected void validateSesionNotNull() {
        if (this.getSession() == null) {
            this.getSessionServerSocket().ifPresent(this::setSession);
        } else
            LOG.warning("Error la session para poder conectarse con el socket es null ");
    }

    protected void sendMessageSocket() {
        try {
            if (this.isSessionNotNULL()) {
                this.getSession().getBasicRemote().sendText("INIT");
                LOG.info("Envio de mensaje para obtener el ID");
            }
        } catch (IOException e) {
            LOG.severe("Error send message socket : ".concat(e.getMessage()));
        }
    }

    private Optional<List<StudentInfoDto>> parseJsonResponse(String jsonResponse) {
        try {
            return Optional.ofNullable(new ObjectMapper()
                    .readValue(jsonResponse,
                            new TypeReference<List<StudentInfoDto>>() {
                            }));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @OnMessage
    public void onMessage(String message) {
        boolean isPresentArray = this.parseJsonResponse(message).isPresent();
        if (isPresentArray) {
            lstStudentInfo = parseJsonResponse(message).get();
            LOG.info("Agregando valores a la tabla para pode generar documento");
        } else {
            this.setSocketId(message);
            LOG.info("Asignacion de ID SOCKET : ".concat(message));
        }
    }

    @OnClose
    public void onClose(Session session) {
        LOG.info("Conexión socket cerrada : ".concat(session.getId()));
    }

    @OnError
    public void onError(Throwable throwable) {
        LOG.warning("ERROR : ".concat(throwable.getMessage()));
    }
}
