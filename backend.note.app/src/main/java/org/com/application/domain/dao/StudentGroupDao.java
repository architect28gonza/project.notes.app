package org.com.application.domain.dao;

import java.util.List;
import com.note.persistence.entitys.GroupEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class StudentGroupDao {

    @Inject
    EntityManager entityManager;

    @Transactional
    public List<GroupEntity> getGroupByTeacherId(String teacherDocument) {
        TypedQuery<GroupEntity> query = entityManager.createQuery(this.getQuery(), GroupEntity.class);
        query.setParameter("teacherDocument", teacherDocument);
        return query.getResultList();
    }

    private String getQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT g FROM StudentGroupEntity sg ")
                .append("JOIN sg.teacher t ")
                .append("JOIN sg.group g ")
                .append("WHERE t.document = :teacherDocument");
        return sql.toString();
    }
}
