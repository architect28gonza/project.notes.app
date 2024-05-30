package org.com.application.util;

import org.com.application.domain.dto.RequestUploadDto;

import com.google.gson.Gson;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectToJsonUtil {

    public static String setObjectToJSON(RequestUploadDto formData) {
        return new Gson().toJson(formData);
    }
}
