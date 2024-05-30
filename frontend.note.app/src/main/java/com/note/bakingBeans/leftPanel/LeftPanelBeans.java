package com.note.bakingBeans.leftPanel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named(value = "bean_left")
@RequestScoped
@Getter
@Setter
public class LeftPanelBeans implements Serializable {
    
    private List<String> lstItemsLeft;

    @PostConstruct
    public void init () {
        this.lstItemsLeft = new ArrayList<>();
        this.setLlenadoLst();
    }

    private void setLlenadoLst() {
        final String[] lstItems = {
            "Principal v1", 
            "Listar reportes", 
            "Realiar reportes masivos",
            "Cambiar informacion",
            "Modificar datos",
            "Reporte de estados"
        };
        for (String item : lstItems) {
            this.lstItemsLeft.add(item);
        }
    }
}
