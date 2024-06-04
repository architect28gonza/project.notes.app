package com.note.bakingBeans;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.note.dto.NotificacionDto;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

@Named(value = "bnotification")
@ViewScoped
public class NotificationBean implements Serializable {
    
    private static final Logger LOG = LoggerFactory.getLogger(NotificationBean.class);
    
    @Getter
    @Setter
    private NotificacionDto notificacionDto;

    public void notifyUser() {
        LOG.info("Notificacion enviada al usuario");
    }

}
