package org.com.application.domain.services;

import org.com.application.domain.dto.ResponseSubjectDto;

public interface TeacherServices {

    ResponseSubjectDto getSubjectsByTeacherId(String teacherDocument);
}
