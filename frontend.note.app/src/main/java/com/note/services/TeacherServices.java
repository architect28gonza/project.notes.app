package com.note.services;

import com.note.dto.TeacherOrClusterDto;

public interface TeacherServices {

    TeacherOrClusterDto getSubjectOrClusterByTeacherDoc(String teacherDocument, boolean isSubject);

}
