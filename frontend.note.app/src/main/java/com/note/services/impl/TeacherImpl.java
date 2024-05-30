package com.note.services.impl;

import com.note.services.TeacherServices;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.ApiHttp;
import com.note.dto.TeacherOrClusterDto;
import com.note.dto.cluster.ResponseClusterDto;
import com.note.dto.teacher.RequestTeacherDto;

@RequestScoped
public class TeacherImpl implements TeacherServices {

    @Inject
    private ApiHttp apiHttp;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public TeacherOrClusterDto getSubjectOrClusterByTeacherDoc(String teacherDocument, boolean isSubject) {
        final String endpoint = isSubject ? "api/v1/teacher/" : "api/v1/group/";
        TeacherOrClusterDto tocDto = new TeacherOrClusterDto();
        Optional<String> json = this.apiHttp.callApi(endpoint.concat(teacherDocument));
        json.ifPresent(itemJson -> {
            try {
                if (isSubject) {
                    tocDto.setRequestTeacherDto(objectMapper.readValue(itemJson, RequestTeacherDto.class));
                } else {
                    tocDto.setResponseClusterDto(objectMapper.readValue(itemJson, ResponseClusterDto.class));
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return tocDto;
    }

}
