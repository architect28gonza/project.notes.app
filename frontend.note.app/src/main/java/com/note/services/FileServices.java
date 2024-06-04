package com.note.services;

import java.util.Optional;

import com.note.dto.FormDto;
import com.note.dto.RequestGenerateXlsxDto;

public interface FileServices {

    public Optional<String> sendFile(FormDto formData);

    public void generateDocumentXlsx(RequestGenerateXlsxDto generateXlsxDto);

}
