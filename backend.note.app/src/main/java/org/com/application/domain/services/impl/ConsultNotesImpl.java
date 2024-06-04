package org.com.application.domain.services.impl;

import java.util.List;

import org.com.application.domain.dto.RequestConsultNotes;
import org.com.application.domain.services.ConsultNotesServices;

import com.note.persistence.entitys.StudentInfoEntity;
import org.com.application.domain.repository.GroupListRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
@SuppressWarnings("unchecked")
public class ConsultNotesImpl implements ConsultNotesServices {

    @PersistenceContext
    private EntityManager entityManager;

    private GroupListRepository groupListRepository;

    @Override
    public List<StudentInfoEntity> consultStudents(RequestConsultNotes consultNotes) {
        String teacherDocument = consultNotes.getTeacherDocument();
        int limit = consultNotes.getLimit();
        int page = consultNotes.getPage();
        int subjectId = consultNotes.getSubjectId();
        long groupId = consultNotes.getGroupId();
        boolean isFilter = consultNotes.isFilter();
        String nameStudent = consultNotes.getNameStudent();

        System.out.println(consultNotes.toString());
        String nameTable = getNameTable(groupId);
        String sqlQuery = buildSqlQuery(nameTable, isFilter, nameStudent);

        return executeQuery(teacherDocument, subjectId, groupId, limit, page, sqlQuery);
    }

    private String getNameTable(long groupId) {
        return groupListRepository.findById(groupId).getName();
    }

    private String buildSqlQuery(String table, boolean isFilter, String nameStudent) {
        return new StringBuilder()
                .append("SELECT ")
                .append("ts.stu_document AS document_student, ")
                .append("ts.stu_name AS name_student, ")
                .append("tc.note_1, ")
                .append("tc.note_2, ")
                .append("tc.note_3, ")
                .append("ROUND(tc.final_note, 2) AS final_note, ")
                .append("CASE WHEN tc.final_note >= 3 THEN 'APROBADO' ELSE 'REPROBADO' END AS status, ")
                .append("tgl.gro_name AS name_group, ")
                .append("tt.tea_name AS name_teacher, ")
                .append("ts2.sub_name AS name_subject, ")
                .append("tu.upl_name_document AS name_document ")
                .append("FROM tbl_students ts ")
                .append("INNER JOIN tbl_students_group tsg ON ts.stu_document = tsg.stu_document ")
                .append("INNER JOIN tbl_group_list tgl ON tgl.gro_id = tsg.gro_id ")
                .append("INNER JOIN tbl_upload tu ON tgl.gro_id = tu.gro_id ")
                .append("INNER JOIN tbl_subjects ts2 ON tu.sub_id = ts2.sub_id ")
                .append("INNER JOIN tbl_teacher tt ON tt.tea_id = ts2.tea_id AND tt.tea_id = tsg.tea_id ")
                .append("INNER JOIN ").append(table).append(" tc ON tc.stu_document = tsg.stu_document ")
                .append("WHERE tt.tea_document = :document ")
                .append("AND ts2.sub_id = :subject ")
                .append("AND tgl.gro_id = :group ")
                .append(isFilter ? "AND ts.stu_name ILIKE '%" + (nameStudent.toLowerCase()) + "%'" : "")
                .toString();
    }

    private List<StudentInfoEntity> executeQuery(String teacherDocument, int subjectId, long groupId, int limit,
            int page, String sqlQuery) {
        Query query = entityManager.createNativeQuery(sqlQuery, StudentInfoEntity.class);
        query.setParameter("document", teacherDocument);
        query.setParameter("subject", subjectId);
        query.setParameter("group", groupId);
        return query.getResultList();
    }
}
