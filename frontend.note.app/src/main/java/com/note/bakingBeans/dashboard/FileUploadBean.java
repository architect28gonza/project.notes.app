package com.note.bakingBeans.dashboard;

import java.io.IOException;
import java.io.Serializable;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.note.services.FileServices;
import com.note.util.FacesMessageUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named(value = "bbeanFile")
@ViewScoped
public class FileUploadBean implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadBean.class);

    @Getter
    @Setter
    private UploadedFile originalImageFile;

    @Inject
    private FormDataBean formDataBean;

    @Inject
    private FileServices fileServices;
    
    @PostConstruct
    public void init() {
        this.originalImageFile = null;
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        UploadedFile file = event.getFile();
        if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
            this.originalImageFile = file;
            this.formDataBean.getFormData().setOriginalImageFile(this.originalImageFile);
            fileServices.sendFile(this.formDataBean.getFormData()).ifPresent(message -> {
                final String fileUpload = this.originalImageFile.getFileName().concat(" " + message);
                FacesMessageUtil.getFacesMessage(FacesMessage.SEVERITY_INFO, "Completado", fileUpload); 
            });
            LOGGER.info("METHOD : handleFileUpload - OUT");
        }
    }
}
