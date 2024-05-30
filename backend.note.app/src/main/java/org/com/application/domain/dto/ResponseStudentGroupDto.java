package org.com.application.domain.dto;

import java.util.List;

import com.note.persistence.entitys.GroupEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStudentGroupDto {
    
    public String message = "";
    public int status = 404;
    public List<GroupEntity> group = null;

}
