package com.note.persistence.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_upload", schema = "shema_note")
public class UploadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upl_id")
    private Long id;

    @Column(name = "tea_document", nullable = false, length = 30)
    private String teacherDocument;

    @Column(name = "sub_id", nullable = false)
    private int subjectId;

    @Column(name = "gro_id", nullable = false)
    private int groupId;

    @Column(name = "upl_name_document", nullable = false, length = 100)
    private String documentName;

    @Column(name = "upl_name_cloud", nullable = false, length = 200)
    private String cloudName;

    @Column(name = "upl_status", nullable = false)
    private boolean status;

    public UploadEntity() {
    }
}
