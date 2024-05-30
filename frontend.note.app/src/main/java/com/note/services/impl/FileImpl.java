package com.note.services.impl;

import java.util.Optional;

import com.note.ApiHttp;
import com.note.dto.FormDto;
import com.note.services.FileServices;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FileImpl implements FileServices {

    @Inject
    private ApiHttp apiHttp;

    @Override
    public Optional<String> sendFile(FormDto formData) {
        return this.apiHttp.uploadFileApi("api/v1/upload", formData);
    }   
}
