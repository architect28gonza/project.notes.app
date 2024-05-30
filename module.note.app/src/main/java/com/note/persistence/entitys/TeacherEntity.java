package com.note.persistence.entitys;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_teacher", schema = "shema_note")
public class TeacherEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tea_id")
    private Long id;

    @Column(name = "tea_name")
    private String name;

    @Column(name = "tea_document")
    private String document;

    @Column(name = "tea_status")
    private boolean status;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubjectEntity> lstSubject = new ArrayList<>();

    TeacherEntity() {}
}