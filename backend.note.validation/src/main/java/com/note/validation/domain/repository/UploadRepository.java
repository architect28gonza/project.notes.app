package com.note.validation.domain.repository;

import com.note.persistence.entitys.UploadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UploadRepository extends JpaRepository<UploadEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE UploadEntity u SET u.status = :status WHERE u.id = :id")
    void updateStatus(@Param("id") Integer id, @Param("status") boolean status);
}