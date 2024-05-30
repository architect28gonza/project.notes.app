package com.note.web.config;

import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;

public class AwsModelConfig {
	
	private String ACCESS_KEY_ID = "";
	private String SECRET_ACCESS_KEY = "";

	private void getCredentiales() {
		final String path = System.getProperty("user.dir");
		try {
            String content = new String(Files.readAllBytes(Paths.get(path.concat("credentialesaws.json"))));
            JSONObject jsonObject = new JSONObject(content);
            this.ACCESS_KEY_ID= jsonObject.getString("ACCESS_KEY_ID");
            this.SECRET_ACCESS_KEY = jsonObject.getString("SECRET_ACCESS_KEY");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private StaticCredentialsProvider getStaticCredentialsProvider() {
		this.getCredentiales();
		return StaticCredentialsProvider.create(
				AwsBasicCredentials.create(
						ACCESS_KEY_ID,
						SECRET_ACCESS_KEY));
	}

	public SqsClient createSqsClient() {
		return SqsClient.builder()
				.region(Region.US_EAST_1)
				.credentialsProvider(getStaticCredentialsProvider())
				.build();
	}

	public SqsAsyncClient createsqsAsyncClient() {
		return SqsAsyncClient.builder()
				.region(Region.US_EAST_1)
				.credentialsProvider(getStaticCredentialsProvider())
				.build();
	}
}
