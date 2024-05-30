package org.com.application.domain.repository;

import com.note.persistence.entitys.GroupEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GroupListRepository implements PanacheRepository<GroupEntity> {
    
}
