package com.note.util;

import jakarta.faces.application.FacesMessage.Severity;
import jakarta.faces.context.FacesContext;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FacesMessageUtil {

    public static void getFacesMessage(Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null,
                new jakarta.faces.application.FacesMessage(severity, summary, detail));
    }
}
