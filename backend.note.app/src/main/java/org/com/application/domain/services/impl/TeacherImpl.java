package org.com.application.domain.services.impl;

import java.util.List;

import org.com.application.domain.dao.TeacherDao;
import org.com.application.domain.dto.ResponseSubjectDto;
import org.com.application.domain.services.TeacherServices;

import com.note.persistence.entitys.SubjectEntity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@ApplicationScoped
@AllArgsConstructor
public class TeacherImpl implements TeacherServices {

    private final TeacherDao teacherDao;

    @Override
    @Transactional
    public ResponseSubjectDto getSubjectsByTeacherId(String teacherDocument) {
        List<SubjectEntity> resultLstSubject = this.teacherDao.getSubjectsByTeacherId(teacherDocument);
        return (!resultLstSubject.isEmpty()) ? new ResponseSubjectDto("INFORMACION", 200, resultLstSubject)
                : new ResponseSubjectDto();
    }
}
