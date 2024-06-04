package org.com.application.web.controller;

import org.com.application.domain.dto.RequestXlsx;
import org.com.application.domain.services.ConsultXlsxServices;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;

@Path("/api/v1")
@AllArgsConstructor
public class GenerateXlsxController {

    private final ConsultXlsxServices consultXlsxServices;

    @POST
    @Path("excel")
    @Consumes(MediaType.APPLICATION_JSON)
    public void consultNotesStudents(RequestXlsx generate) {
        System.out.println(generate.toString());
        this.consultXlsxServices.consultarDataGenerateXlsx(generate);
    }
}
