package org.com.application.domain.dto;

import java.util.List;

import com.note.persistence.entitys.SubjectEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSubjectDto {

    public String message = "";
    public int status = 404;
    public List<SubjectEntity> subject = null;

}
