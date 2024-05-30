package com.note.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.note.dto.FormDto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConvertDtoToJson {

    public static String setDtoToJsonString(FormDto dtoConvert) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(dtoConvert);
        } catch (Exception e) {
            return null;
        }
    }

}
