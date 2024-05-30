package org.com.application.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestConsultNotes {
    
    private int page;
    private int limit;
    private String teacherDocument;
    private int subjectId;
    private int groupId;
}
