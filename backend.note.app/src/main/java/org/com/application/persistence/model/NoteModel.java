package org.com.application.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class NoteModel {

    private double note_1;
    private double note_2;
    private double note_3;
    private double final_note;
}
