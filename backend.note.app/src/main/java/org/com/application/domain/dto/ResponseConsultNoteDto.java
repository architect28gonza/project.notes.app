package org.com.application.domain.dto;

import java.util.List;

import com.note.persistence.entitys.StudentInfoEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseConsultNoteDto {
    
    private String message;
    private int status;
    private List<StudentInfoEntity> lstStudents;

}
