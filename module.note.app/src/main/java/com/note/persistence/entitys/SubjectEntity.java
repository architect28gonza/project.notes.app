package com.note.persistence.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_subjects", schema = "shema_note")
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "sub_id")
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(name = "sub_name", length = 50, nullable = false)
    private String name;

    @Column(name = "sub_status", nullable = false)
    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "tea_id", referencedColumnName = "tea_id", nullable = false)
    private TeacherEntity teacher;

    SubjectEntity() {}
}

