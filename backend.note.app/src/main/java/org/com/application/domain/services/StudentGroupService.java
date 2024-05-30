package org.com.application.domain.services;

import org.com.application.domain.dto.ResponseStudentGroupDto;

public interface StudentGroupService {
    
    ResponseStudentGroupDto getStudentGroup(String teacherDocument);

}
