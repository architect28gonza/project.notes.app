package com.note.validation.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;
import com.note.web.config.AwsModelConfig;

@Configuration
public class AwsConfig {

    private final AwsModelConfig AwsS3Config = new AwsModelConfig();
        
    @Bean
    public SqsClient createSqsClient() {
        return AwsS3Config.createSqsClient();
    }

    @Bean
    public SqsAsyncClient createS3ClientAsync() {
        return AwsS3Config.createsqsAsyncClient();
    }
    
}
