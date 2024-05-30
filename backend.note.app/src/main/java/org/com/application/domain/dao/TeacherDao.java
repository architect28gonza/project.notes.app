package org.com.application.domain.dao;

import java.util.List;

import com.note.persistence.entitys.SubjectEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@SuppressWarnings("unchecked")
@ApplicationScoped
public class TeacherDao {

    @Inject
    EntityManager entityManager;

    @Transactional
    public List<SubjectEntity> getSubjectsByTeacherId(String teacherDocument) {
        final String qlString = "SELECT s FROM TeacherEntity t JOIN t.lstSubject s WHERE t.document = :document";
        Query query = entityManager.createQuery(qlString);
        query.setParameter("document", teacherDocument);
        return query.getResultList();
    }
}
