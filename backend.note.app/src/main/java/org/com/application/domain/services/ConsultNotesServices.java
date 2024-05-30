package org.com.application.domain.services;

import org.com.application.domain.dto.RequestConsultNotes;
import org.com.application.domain.dto.ResponseConsultNoteDto;

public interface ConsultNotesServices {
    
    public ResponseConsultNoteDto consultStudents(RequestConsultNotes consultNotes);

}
