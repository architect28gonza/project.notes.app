package org.com.application.domain.services;

import java.util.Optional;

import org.com.application.domain.dto.RequestUploadDto;

public interface UploadServices {
    
    public Optional<String> uploadFile(RequestUploadDto formData);

    public void save(RequestUploadDto formData);
}
