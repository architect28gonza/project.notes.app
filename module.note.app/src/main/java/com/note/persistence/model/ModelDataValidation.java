package com.note.persistence.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ModelDataValidation {
    
    private List<ModelNote> lstStudentsNote;
    private StringBuilder sbParamWhere;
    private StringBuilder sbLogError;
}
