package com.note.services;

import java.util.Optional;

import com.note.dto.FormDto;

public interface FileServices {

    public Optional<String> sendFile(FormDto formData);

}
