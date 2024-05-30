package org.com.application.web.config;

import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.services.sqs.SqsClient;
import com.note.web.config.AwsModelConfig;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class AwsConfig {
	
	private final AwsModelConfig AwsS3Config = new AwsModelConfig();

	@Produces
	@ApplicationScoped
	public SqsClient createSqsClient() {
		return AwsS3Config.createSqsClient();
	}
}
