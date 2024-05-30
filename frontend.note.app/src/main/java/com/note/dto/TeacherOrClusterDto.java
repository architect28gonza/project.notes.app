package com.note.dto;

import com.note.dto.cluster.ResponseClusterDto;
import com.note.dto.teacher.RequestTeacherDto;

import lombok.Getter;
import lombok.Setter;

public class TeacherOrClusterDto {
    @Getter
    @Setter
    private RequestTeacherDto requestTeacherDto;

    @Getter
    @Setter
    private ResponseClusterDto responseClusterDto;

}
