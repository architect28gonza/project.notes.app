package org.com.application.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestXlsx {

    private String socketId;
    private String teacherDocument;
    // private Integer documentId; /* Id de la lista del documento de Upload */
    private Integer subjectId;
    private Integer tableNameId;
}
