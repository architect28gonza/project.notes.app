package com.note.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentInfoDto {
    
    private String documentStudent;
    private String nameStudent;
    private Double note1;
    private Double note2;
    private Double note3;
    private Double finalNote;
    private String status;
    private String nameGroup;
    private String nameTeacher;
    private String nameSubject;
    private String nameDocument;
}
