package com.note.persistence.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbl_students_group", schema = "shema_note")
public class StudentGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "stu_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tea_id", referencedColumnName = "tea_id", foreignKey = @ForeignKey(name = "fk_student_group_teacher"))
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "gro_id", referencedColumnName = "gro_id", foreignKey = @ForeignKey(name = "fk_student_group_group"))
    private GroupEntity group;
    
    StudentGroupEntity() {
    }
}
