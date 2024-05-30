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
@Table(name = "tbl_group_list", schema = "shema_note")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "gro_id")
    private Integer id;

    @Column(name = "gro_name", length = 100, nullable = false)
    private String name;

    @Column(name = "gro_status", nullable = false)
    private boolean status = true;

    GroupEntity() {
    }
}
