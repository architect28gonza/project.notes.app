package org.com.application.domain.dto;

import java.io.InputStream;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUploadDto {
    
    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream file;

    @FormParam("teacherDocument")
    @PartType(MediaType.TEXT_PLAIN)
    private String teacherDocument;

    @FormParam("tableNameId")
    @PartType(MediaType.TEXT_PLAIN)
    private String SubjectId;

    @FormParam("subjectId")
    @PartType(MediaType.TEXT_PLAIN)
    private String groupId;

    @FormParam("nameFile")
    @PartType(MediaType.TEXT_PLAIN)
    private String nameFileS3;

}
