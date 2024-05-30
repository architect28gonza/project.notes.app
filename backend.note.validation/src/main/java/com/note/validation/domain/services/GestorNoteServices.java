package com.note.validation.domain.services;

import java.util.List;

public interface GestorNoteServices {

    public void setGestionNote(
            String nameTable,
            List<Long> lstIds, 
            List<Double> lstPromedio);

}
