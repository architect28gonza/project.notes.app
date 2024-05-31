package com.note.services.impl;

import java.util.List;
import java.util.Optional;
import com.note.ApiHttp;
import com.note.dto.RequestDataNote;
import com.note.dto.StudentInfoDto;
import com.note.services.ConsultNoteServices;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ConsultNoteImpl implements ConsultNoteServices {

    @Inject
    private ApiHttp apiHttp;

    @Override
    public Optional<List<StudentInfoDto>> getDataNoteStudents(RequestDataNote dataNoteConsult) {
        final String endpoint = "api/v1/notes";
        return apiHttp.consultNoteStudent(dataNoteConsult, endpoint);
    }
}
