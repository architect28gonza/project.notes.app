package org.com.application.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestConsultNotes {
    
    private int page;
    private int limit;
    private String teacherDocument;
    private int subjectId;
    private int groupId;
    private String nameStudent;
    private boolean filter;
}
