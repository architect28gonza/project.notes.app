package com.note.dto.teacher;

import java.util.List;

import com.note.dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;

public class RequestTeacherDto extends ResponseDto {

    @Getter
    @Setter
    private List<Subject> subject;
}