package com.note;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.note.dto.FormDto;
import com.note.dto.RequestGenerateXlsxDto;
import com.note.dto.RequestDataNote;
import com.note.dto.StudentInfoDto;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApiHttp {

    private static final String API_URL = "http://localhost:8081/";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Gson GSON = new Gson();

    public Optional<String> callApi(String endPoint) {
        return executeGetRequest(API_URL.concat(endPoint));
    }

    public Optional<String> uploadFileApi(String endpoint, FormDto formData) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(API_URL.concat(endpoint));
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            builder.addTextBody("teacherDocument", formData.getTeacherDocument());
            builder.addTextBody("tableNameId", String.valueOf(formData.getTableNameId()));
            builder.addTextBody("subjectId", String.valueOf(formData.getSubjectId()));

            try (InputStream inputStream = formData.getOriginalImageFile().getInputStream()) {
                builder.addBinaryBody("file", inputStream, ContentType.APPLICATION_OCTET_STREAM,
                        formData.getOriginalImageFile().getFileName());

                httpPost.setEntity(builder.build());

                return executePostRequest(httpPost);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<List<StudentInfoDto>> consultNoteStudent(RequestDataNote consultNote, String endpoint) {
        String fullUrl = API_URL.concat(endpoint);
        String json = GSON.toJson(consultNote);
        HttpPost httpPost = new HttpPost(fullUrl);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        return executePostRequest(httpPost)
                .map(this::parseStudentInfoDtoList);
    }

    private List<StudentInfoDto> parseStudentInfoDtoList(String json) {
        try {
            return OBJECT_MAPPER.readValue(json, new TypeReference<List<StudentInfoDto>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void generateDocuemntoPost(String endpoint, RequestGenerateXlsxDto generateXlsxDto) {
        String fullUrl = API_URL.concat(endpoint);
        String json = GSON.toJson(generateXlsxDto);
        HttpPost httpPost = new HttpPost(fullUrl);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));

        executePostRequest(httpPost).ifPresent(System.out::println);
    }

    private Optional<String> executeGetRequest(String url) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                return handleResponse(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Optional<String> executePostRequest(HttpPost httpPost) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(httpPost)) {
            return handleResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Optional<String> handleResponse(CloseableHttpResponse response) throws IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            return Optional.ofNullable(entity != null ? EntityUtils.toString(entity) : null);
        } else {
            return Optional.of("Error: " + statusCode);
        }
    }
}
