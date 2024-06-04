package org.com.application.domain.services.impl;

import java.io.IOException;
import java.util.List;

import org.com.application.Socket;
import org.com.application.domain.dto.RequestConsultNotes;
import org.com.application.domain.dto.RequestXlsx;
import org.com.application.domain.services.ConsultNotesServices;
import org.com.application.domain.services.ConsultXlsxServices;

import com.google.gson.Gson;
import com.note.persistence.entitys.StudentInfoEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.Session;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class ConsultXlsxImpl implements ConsultXlsxServices {

    private final Socket socket;

    private final ConsultNotesServices consultNotesServices;

    @Override
    public void consultarDataGenerateXlsx(RequestXlsx generate) {
        Session session = socket.getSessionById(generate.getSocketId());
        RequestConsultNotes consultNotes = getRequestConsultNotes(generate);
        List<StudentInfoEntity> lstStudents = consultNotesServices.consultStudents(consultNotes);
        if (session != null && session.isOpen()) {
            try {
                String jsonStrin = new Gson().toJson(lstStudents);
                session.getBasicRemote().sendText(jsonStrin);
            } catch (IOException e) {
                System.err.println("No s eha podido enviar el mensaje al clientSocket : ".concat(e.getMessage()));
            }
        } else {
            System.err.println("Sesión WebSocket no encontrada o cerrada");
        }
    }

    private RequestConsultNotes getRequestConsultNotes(RequestXlsx generate) {
        return RequestConsultNotes
                .builder()
                .page(1)
                .limit(100)
                .teacherDocument(generate.getTeacherDocument())
                .subjectId(generate.getSubjectId())
                .groupId(generate.getTableNameId())
                .filter(false)
                .build();
    }

}
