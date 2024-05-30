package org.com.application.domain.services.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.persistence.Query;

import org.com.application.domain.dto.RequestUploadDto;
import org.com.application.domain.repository.GroupListRepository;
import org.com.application.domain.services.UploadServices;
import org.com.application.util.InputStreamToFileUtil;

import com.note.persistence.entitys.GroupEntity;
import com.note.persistence.entitys.UploadEntity;
import com.note.web.util.UtilFile;
import com.note.web.util.UtilLogger;
import com.note.web.util.constants.Constants;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.com.application.domain.repository.UploadRepository;

@ApplicationScoped
@AllArgsConstructor
public class UploadImpl implements UploadServices {

    private final UploadRepository uploadRepository;
    private final GroupListRepository groupListRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<String> uploadFile(RequestUploadDto formData) {
        try {
            Optional<String> uploadedFilePath = InputStreamToFileUtil.inputStreamToFile(
                    formData.getTeacherDocument(), formData.getFile());

            uploadedFilePath.ifPresent(filePath -> {
                String fullFilePath = getFullFilePath(filePath);
                executeUpdateQuery(getTableName(formData), fullFilePath);
            });
            return uploadedFilePath;
        } catch (IOException e) {
            UtilLogger.error("Error uploadFile update query: ", e.getMessage());
            return Optional.empty();
        }
    }

    private String getFullFilePath(String uploadedFileName) {
        return Constants.PATH_DIRECOTRY.concat(uploadedFileName);
    }

    private void executeUpdateQuery(String tableName, String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            var stream = reader.readAll().stream();
            getValuesPart(stream).ifPresent(values -> {
                String sqlQuery = "INSERT INTO " + tableName + " VALUES " + values + ";";
                if (!sqlQuery.endsWith("VALUES ;")) {
                    /* Ejecutamos la consulta para instar en la base de datos */
                    Query nativeQuery = entityManager.createNativeQuery(sqlQuery);
                    nativeQuery.executeUpdate();
                    /* Eliminamos el archivo en la carpeta tmp dentro del proyecto */
                    UtilFile.removeFileTmp(new File(filePath));
                }
            });
        } catch (IOException | CsvException e) {
            UtilLogger.error("Error - NO se puede construir la consulta : ", e.getMessage());
        }
    }

    private Optional<String> getValuesPart(Stream<String[]> stream) {
        return Optional.of(stream.map(row -> {
            String[] trimmedRow = new String[4];
            System.arraycopy(row, 0, trimmedRow, 0, Math.min(row.length, 4));

            return "(" + Stream.of(trimmedRow)
                    .map(value -> "\'" + value + "\'")
                    .collect(Collectors.joining(", ")) + ")";

        }).collect(Collectors.joining(", ")));
    }

    private String getTableName(RequestUploadDto formData) {
        return getClusterName(Long.parseLong(formData.getGroupId())).orElse("");
    }

    private Optional<String> getClusterName(Long groupId) {
        GroupEntity group = groupListRepository.findById(groupId);
        return (group != null) ? Optional.of(group.getName()) : Optional.empty();
    }

    @Override
    @Transactional
    public void save(RequestUploadDto formData) {
        uploadRepository.persist(createUploadEntity(formData));
    }

    private UploadEntity createUploadEntity(RequestUploadDto formData) {
        UploadEntity uploadEntity = new UploadEntity();
        uploadEntity.setTeacherDocument(formData.getTeacherDocument());
        uploadEntity.setSubjectId(Integer.parseInt(formData.getSubjectId()));
        uploadEntity.setGroupId(Integer.parseInt(formData.getGroupId()));
        uploadEntity.setDocumentName(formData.getNameFileS3());
        uploadEntity.setCloudName(formData.getNameFileS3());
        return uploadEntity;
    }
}