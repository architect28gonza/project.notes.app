package com.note;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
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

    public Optional<String> callApi(String endPoint) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(API_URL.concat(endPoint));
        try {
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            return Optional
                    .of((statusCode == 200) ? EntityUtils.toString(response.getEntity()) : "Error: " + statusCode);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public Optional<String> uploadFileApi(String endpoint, FormDto formData) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(API_URL.concat(endpoint));
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            builder.addTextBody("teacherDocument", formData.getTeacherDocument());
            builder.addTextBody("tableNameId", String.valueOf(formData.getTableNameId()));
            builder.addTextBody("subjectId", String.valueOf(formData.getSubjectId()));

            InputStream inputStream = formData.getOriginalImageFile().getInputStream();
            builder.addBinaryBody("file", inputStream, ContentType.APPLICATION_OCTET_STREAM,
                    formData.getOriginalImageFile().getFileName());

            HttpEntity multipart = builder.build();
            httpPost.setEntity(multipart);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                return Optional.of(EntityUtils.toString(entity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<List<StudentInfoDto>> consultNoteStudent(RequestDataNote consultNote, String endpoint) {
        String fullUrl = API_URL.concat(endpoint);
        Gson gson = new Gson();
        String json = gson.toJson(consultNote);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(fullUrl);

            // Configurar los encabezados de la petición si es necesario
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Accept", "application/json");

            StringEntity stringEntity = new StringEntity(json);
            httpPost.setEntity(stringEntity);

            // Enviar la petición y obtener la respuesta
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String responseString = EntityUtils.toString(responseEntity);
                    return Optional.ofNullable(parseJsonResponse(responseString));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private List<StudentInfoDto> parseJsonResponse(String jsonResponse) throws IOException {
        try {
            return new ObjectMapper()
                    .readValue(jsonResponse,
                            new TypeReference<List<StudentInfoDto>>() {
                            });
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }


    public void generateDocuemntoPost(String endpoint, RequestGenerateXlsxDto generateXlsxDto) {
        String fullUrl = API_URL.concat(endpoint);
        Gson gson = new Gson();
        String json = gson.toJson(generateXlsxDto);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(fullUrl);

            // Configurar los encabezados de la petición si es necesario
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Accept", "application/json");

            StringEntity stringEntity = new StringEntity(json);
            httpPost.setEntity(stringEntity);

            // Enviar la petición y obtener la respuesta
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String responseString = EntityUtils.toString(responseEntity);
                    System.out.println(responseString);
                    // return Optional.ofNullable(parseJsonResponse(responseString));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return Optional.empty();
    }

}
