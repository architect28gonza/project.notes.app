package com.note.persistence.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "upl_id", nullable = false)
    private Integer idUp;

    @Column(name = "stu_document", nullable = false)
    private Integer studentDocument;

    @Column(name = "note_1")
    private Double note1;

    @Column(name = "note_2")
    private Double note2;

    @Column(name = "note_3")
    private Double note3;

    @Column(name = "final_note")
    private Double finalNote;

    @Column(name = "status", columnDefinition = "boolean default false")
    private Boolean status;
}
