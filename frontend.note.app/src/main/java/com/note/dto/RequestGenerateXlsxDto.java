package com.note.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestGenerateXlsxDto {
    private String socketId;
    private String teacherDocument;
    private int tableNameId;
    private int subjectId;
}
