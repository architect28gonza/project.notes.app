package org.com.application.web.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import org.com.application.domain.dto.ResponseSubjectDto;
import org.com.application.domain.services.TeacherServices;

@Path("/api/v1")
public class TeacherController {

    @Inject
    TeacherServices teacherServices;

    @GET
    @Path("teacher/{teacherDocument}")
    public Response getSubjectsByTeacherId(@PathParam("teacherDocument") String teacherDocument) {
        ResponseSubjectDto subjects = teacherServices.getSubjectsByTeacherId(teacherDocument);
        return Response.ok(subjects).status(subjects.getStatus()).build();
    }

}
