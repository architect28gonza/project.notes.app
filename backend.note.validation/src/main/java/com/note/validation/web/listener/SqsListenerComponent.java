package com.note.validation.web.listener;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.note.validation.domain.services.ProcessFileServices;
import com.note.validation.persistence.model.StructureJson;

import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SqsListenerComponent {

    private final ProcessFileServices processFileServices;

    @SqsListener("cola-note-dev")
    public void receiveMessage(String message) {
        Optional<StructureJson> structure = parseJson(message);
        structure.ifPresent(this.processFileServices::processFile);
    }

    private Optional<StructureJson> parseJson(String jsonStringSqs) {
        try {
            return Optional.of(new Gson().fromJson(jsonStringSqs, StructureJson.class));
        } catch (JsonSyntaxException e) {
            return Optional.empty();
        }
    }

}
