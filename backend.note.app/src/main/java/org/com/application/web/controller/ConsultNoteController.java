package org.com.application.web.controller;

import java.util.List;

import org.com.application.domain.dto.RequestConsultNotes;
import org.com.application.domain.services.ConsultNotesServices;
import com.note.persistence.entitys.StudentInfoEntity;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;

@Path("/api/v1")
@AllArgsConstructor
public class ConsultNoteController {

    private final ConsultNotesServices consultNotesServices;

    @POST
    @Path("notes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultNotesStudents(RequestConsultNotes consult) {
        List<StudentInfoEntity> consultNote = this.consultNotesServices.consultStudents(consult);
        return Response.ok(consultNote).status(consultNote.isEmpty() ? 404 : 200).build();
    }
}
