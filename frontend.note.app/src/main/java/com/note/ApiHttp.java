package com.note;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.note.dto.FormDto;

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
}
