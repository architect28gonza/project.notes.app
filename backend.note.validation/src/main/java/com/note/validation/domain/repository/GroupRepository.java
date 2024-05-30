package com.note.validation.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.note.persistence.entitys.GroupEntity;

public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {
    
}
