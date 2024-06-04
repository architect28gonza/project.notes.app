package org.com.application.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Getter
@Setter
public class RequestConsultNotes {
    
    private int page;
    private int limit;
    private String teacherDocument;
    private int subjectId;
    private int groupId;
    private String nameStudent;
    private boolean filter;
}
