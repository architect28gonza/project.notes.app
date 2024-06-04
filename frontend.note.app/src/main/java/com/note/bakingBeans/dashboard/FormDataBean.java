package com.note.bakingBeans.dashboard;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.note.dto.TeacherOrClusterDto;
import com.note.dto.cluster.Group;
import com.note.dto.cluster.ResponseClusterDto;
import com.note.dto.teacher.RequestTeacherDto;
import com.note.dto.teacher.Subject;
import com.note.services.TeacherServices;
import com.note.dto.FormDto;

@Getter
@Setter
@Named(value = "bbeanForm")
@ViewScoped
public class FormDataBean implements Serializable {

    private FormDto formData;
    private List<Subject> lstSubjects;
    private List<Group> lstGroup;

    @Inject
    private TeacherServices teacherServices;

    @PostConstruct
    public void init() {
        this.formData = new FormDto();
        this.lstSubjects = new ArrayList<>();
        this.lstGroup = new ArrayList<>();
    }

    public void handlesearchTeacherDocument() {
        if (!this.getFormData().getTeacherDocument().isEmpty()) {
            TeacherOrClusterDto teacherOrClusterDto = this.teacherServices
                    .getSubjectOrClusterByTeacherDoc(this.getFormData().getTeacherDocument(), true);
            RequestTeacherDto requestTeacherDto = teacherOrClusterDto.getRequestTeacherDto();
            if (requestTeacherDto != null) {
                if (requestTeacherDto.getStatus() == 200) {
                    this.setLstSubjects(requestTeacherDto.getSubject());
                }
            } else {
                addMessage(FacesMessage.SEVERITY_WARN, "Documento",
                        "El num. de documento no es valido o no existe");
            }
        }
    }

    public void handleSubjectSelection() {
        if (this.getFormData().getSubjectId() != 0) {
            TeacherOrClusterDto teacherOrClusterDto = this.teacherServices
                    .getSubjectOrClusterByTeacherDoc(this.getFormData().getTeacherDocument(), false);
            ResponseClusterDto requestTeacherDto = teacherOrClusterDto.getResponseClusterDto();
            if (requestTeacherDto != null) {
                if (requestTeacherDto.getStatus() == 200) {
                    this.setLstGroup(requestTeacherDto.getGroup());
                }
            } else {
                addMessage(FacesMessage.SEVERITY_WARN, "Asignaruta",
                        "El num. de la asignatura no es valido o no existe");
            }
        }
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }
}
