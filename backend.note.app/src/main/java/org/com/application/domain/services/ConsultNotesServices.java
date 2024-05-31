package org.com.application.domain.services;

import java.util.List;

import org.com.application.domain.dto.RequestConsultNotes;

import com.note.persistence.entitys.StudentInfoEntity;

public interface ConsultNotesServices {
    
    public List<StudentInfoEntity> consultStudents(RequestConsultNotes consultNotes);

}
