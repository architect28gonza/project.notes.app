package org.com.application.domain.repository;

import com.note.persistence.entitys.UploadEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UploadRepository implements PanacheRepository<UploadEntity> {
    
}
