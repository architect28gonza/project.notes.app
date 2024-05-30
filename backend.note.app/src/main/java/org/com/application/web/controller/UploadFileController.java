package org.com.application.web.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.com.application.domain.dto.RequestUploadDto;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.com.application.domain.services.SqsServices;
import org.com.application.domain.services.UploadServices;
import org.com.application.util.ObjectToJsonUtil;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;

@Path("api/v1/upload")
@AllArgsConstructor
public class UploadFileController {

    private final UploadServices uploadServices;

    private final SqsServices sqsServices;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadFile(@MultipartForm RequestUploadDto formData) throws FileNotFoundException, IOException {
        if (uploadServices.uploadFile(formData).isPresent()) {
            /* Obtener el nombre del documento generado */
            String fileNameS3 = uploadServices.uploadFile(formData).get();
            
            formData.setNameFileS3(fileNameS3);
            formData.setFile(null);

            /* Registro en la base de datos */
            this.uploadServices.save(formData);

            /* Enviar Json String al SQS de AWS */
            String jsonString = ObjectToJsonUtil.setObjectToJSON(formData);
            this.sqsServices.sendAmazonSqs(jsonString);
            
            return Response.ok("El archivo fue subido con exito.").status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Error al recibir el archivo").build();
    }
}
