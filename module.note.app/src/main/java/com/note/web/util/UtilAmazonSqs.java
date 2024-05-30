package com.note.web.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UtilAmazonSqs {

    public static GetQueueUrlRequest getQueueUrlRequest(String queueName) {
        return GetQueueUrlRequest.builder()
                .queueName(queueName)
                .build();
    }

    public static SendMessageRequest getSendMessageRequest(String messageBody, String queueUrl) {
        return SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageBody(messageBody)
                .delaySeconds(5)
                .build();
    }

}
