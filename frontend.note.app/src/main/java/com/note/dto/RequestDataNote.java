package com.note.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Builder
public class RequestDataNote {
    private int page;
    private int limit;
    private String teacherDocument;
    private int subjectId;
    private int groupId;
    private String nameStudent;
    private boolean filter;
}
