package com.note.services;

import java.util.List;
import java.util.Optional;

import com.note.dto.RequestDataNote;
import com.note.dto.StudentInfoDto;

public interface ConsultNoteServices {
    
    Optional<List<StudentInfoDto>> getDataNoteStudents(RequestDataNote dataNoteConsult);  
}
