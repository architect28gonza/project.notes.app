package com.note.persistence.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ModelNote {
    
    private String document;
    private double note1;
    private double note2;
    private double note3;
    private double noteFinal;

}
