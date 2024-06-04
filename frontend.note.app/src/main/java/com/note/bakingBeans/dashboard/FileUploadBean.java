package com.note.bakingBeans.dashboard;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.note.dto.RequestDataNote;
import com.note.dto.StudentInfoDto;
import com.note.services.ConsultNoteServices;
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

    @Getter
    @Setter
    private List<StudentInfoDto> lstStudentInfo = new ArrayList<>();

    @Getter
    @Setter
    private int page;

    @Getter
    @Setter
    private int limit;

    @Getter
    @Setter
    private String nameStudent;

    @Inject
    private FormDataBean formDataBean;
    @Inject
    private FileServices fileServices;
    @Inject
    private ConsultNoteServices consultNoteServices;

    @PostConstruct
    public void init() {
        this.originalImageFile = null;
        this.setNameStudent("");
        this.setPage(1);
        this.setLimit(10);
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
            LOGGER.info("Archivo enviado para ser procesado - out");
        }
    }

    public void consultNotes() {
        RequestDataNote noteRequest = this.getRequestDataNote(false);
        Optional<List<StudentInfoDto>> lstStudentInfo = consultNoteServices.getDataNoteStudents(noteRequest);
        lstStudentInfo.ifPresent(this::setLstStudentInfo);
        LOGGER.info("Consulta de notas para mostrar tabla- out");
    }

    public void consultStudentFilter() {
        RequestDataNote noteRequest = this.getRequestDataNote(true);
        Optional<List<StudentInfoDto>> lstStudentInfo = consultNoteServices.getDataNoteStudents(noteRequest);
        lstStudentInfo.ifPresent(this::setLstStudentInfo);
        LOGGER.info("Consulta de nota por filtro de busqueda - out");
    }

    private RequestDataNote getRequestDataNote(boolean entradaFilter) {
        String teacherDocument = this.formDataBean.getFormData().getTeacherDocument();
        int subjectId = this.formDataBean.getFormData().getSubjectId();
        int groupId = this.formDataBean.getFormData().getTableNameId();
        return RequestDataNote
                .builder()
                .limit(limit)
                .page(page)
                .subjectId(subjectId)
                .teacherDocument(teacherDocument)
                .groupId(groupId)
                .nameStudent(nameStudent)
                .filter(entradaFilter)
                .build();
    }
}
