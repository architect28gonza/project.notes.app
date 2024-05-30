package com.note;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Named(value = "index_bean")
@ViewScoped
@Getter
@Setter
public class IndexBean implements Serializable {

    private String informacion;

    @PostConstruct
    public void init() {
        this.informacion = "Lo ha logrado!!";
    }
}