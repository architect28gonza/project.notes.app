package com.note.bakingBeans;

import java.io.Serializable;
import java.util.logging.Logger;

import com.note.bakingBeans.dashboard.FormDataBean;
import com.note.dto.RequestGenerateXlsxDto;
import com.note.services.FileServices;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.websocket.ClientEndpoint;
import jakarta.websocket.ContainerProvider;
import lombok.Getter;
import lombok.Setter;

@ClientEndpoint
@ApplicationScoped
@Named(value = "generateBean")
public class GenerateXlsx extends ClientSocket implements Serializable {

    private static final Logger LOG = Logger.getLogger(GenerateXlsx.class.getName());

    @Inject
    private FormDataBean formDataBean;

    @Inject
    private FileServices fileServices;

    @Getter
    @Setter
    private String message;

    @PostConstruct
    public void init() {
        this.setContainerSupplier(ContainerProvider::getWebSocketContainer);
        this.setContainer(this.getContainerSupplier().get());
    }

    public void sendMessageServerSocket() {
        try {
            this.validateSesionNotNull();
            this.sendMessageSocket();
        } catch (Exception e) {
            LOG.severe("Accion no completada, No se puede acceder al socket : "
                    .concat(e.getMessage()));
        }
    }

    public void generateDocument() {
        RequestGenerateXlsxDto generateXlsxDto = RequestGenerateXlsxDto
                .builder()
                .socketId(this.getSocketId())
                .teacherDocument(formDataBean.getFormData().getTeacherDocument())
                .subjectId(formDataBean.getFormData().getSubjectId())
                .tableNameId(formDataBean.getFormData().getTableNameId())
                .build();
        this.fileServices.generateDocumentXlsx(generateXlsxDto);
        LOG.info("Proceso de generacion de documento completado");
    }
}
