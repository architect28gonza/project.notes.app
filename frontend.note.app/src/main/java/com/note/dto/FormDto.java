package com.note.dto;
import org.primefaces.model.file.UploadedFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormDto {
    
    private UploadedFile originalImageFile;
    private String teacherDocument;
    private int tableNameId;
    private int subjectId;
}
