package com.note.persistence.entitys;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_students", schema = "shema_note")
public class StudentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stu_id")
    private Long stuId;

    @Column(name = "stu_document", nullable = false, unique = true)
    private int stuDocument;

    @Column(name = "stu_name", nullable = false, length = 100)
    private String stuName;

    @Column(name = "stu_phone", length = 20)
    private String stuPhone;

    @Column(name = "stu_age", nullable = false)
    private short stuAge;

    @Column(name = "stu_sex", nullable = false, length = 12)
    private String stuSex;

    @Column(name = "stu_status", nullable = false)
    private boolean stuStatus;

    public StudentEntity() {}
    
}
