package org.com.application.domain.services.impl;

import org.com.application.domain.dao.StudentGroupDao;
import org.com.application.domain.dto.ResponseStudentGroupDto;
import org.com.application.domain.services.StudentGroupService;
import com.note.persistence.entitys.GroupEntity;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StudentGroupImpl implements StudentGroupService {

    @Inject
    StudentGroupDao studentGroupDao;

    @Override
    public ResponseStudentGroupDto getStudentGroup(String teacherDocument) {
        List<GroupEntity> lstGroup = this.studentGroupDao.getGroupByTeacherId(teacherDocument);
        return !lstGroup.isEmpty()
            ? new ResponseStudentGroupDto("GRUPO ASIGNADOS", 200, lstGroup)
            : new ResponseStudentGroupDto();
    }
    
}
