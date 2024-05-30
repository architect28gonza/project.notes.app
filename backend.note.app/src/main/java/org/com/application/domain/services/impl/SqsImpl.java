package org.com.application.domain.services.impl;

import static com.note.web.util.constants.AwsConstants.NAME_QUEUE;
import org.com.application.domain.services.SqsServices;
import com.note.web.util.UtilAmazonSqs;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import software.amazon.awssdk.services.sqs.SqsClient;

@ApplicationScoped
@AllArgsConstructor
public class SqsImpl implements SqsServices {

    private final SqsClient sqsClient;

    @Override
    public void sendAmazonSqs(String message) {
        final String queueUrl = sqsClient.getQueueUrl(UtilAmazonSqs.getQueueUrlRequest(NAME_QUEUE)).queueUrl();
        sqsClient.sendMessage(UtilAmazonSqs.getSendMessageRequest(message, queueUrl));
    }
}
