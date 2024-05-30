package org.com.application.web.controller;

import org.com.application.domain.dto.ResponseStudentGroupDto;
import org.com.application.domain.services.StudentGroupService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1")
public class GroupController {

    @Inject
    StudentGroupService studentGroupService;

    @GET
    @Path("group/{teacherDocument}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupById(@PathParam("teacherDocument") String teacherId) {
        ResponseStudentGroupDto group = studentGroupService.getStudentGroup(teacherId);
        return Response.ok(group).status(group.getStatus()).build();
    }
}
