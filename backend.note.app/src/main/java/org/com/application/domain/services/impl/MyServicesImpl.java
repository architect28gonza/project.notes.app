package org.com.application.domain.services.impl;

import org.com.application.domain.services.MyServices;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyServicesImpl implements MyServices {

    @Override
    public String getMessageWelcome() {
        return "Hello friends, Welcome!!";
    }
    
}
