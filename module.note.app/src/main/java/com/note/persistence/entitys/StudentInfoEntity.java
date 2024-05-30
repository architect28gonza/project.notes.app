package com.note.persistence.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class StudentInfoEntity {

    @Id
    @Column(name = "document_student")
    private String documentStudent;

    @Column(name = "name_student")
    private String nameStudent;

    @Column(name = "note_1")
    private Double note1;

    @Column(name = "note_2")
    private Double note2;

    @Column(name = "note_3")
    private Double note3;

    @Column(name = "final_note")
    private Double finalNote;

    @Column(name = "status")
    private String status;

    @Column(name = "name_group")
    private String nameGroup;

    @Column(name = "name_teacher")
    private String nameTeacher;

    @Column(name = "name_subject")
    private String nameSubject;

    @Column(name = "name_document")
    private String nameDocument;
}
