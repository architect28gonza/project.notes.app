package com.note.web.config;

import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;

public class AwsModelConfig {
	
	@Setter
	@Getter
	private String accessKeyId="";
	
	@Setter
	private String secrectAccessId="";

	public AwsModelConfig () {
		this.getCredentials();
	}


	private void getCredentials() {
		/* Se debe tener encuenta que como es modo preuba, Se coloca la ruta fija */
		final String path = "/media/architech/Archivos/Proyectos Programacion/project.notes.app/module.note.app".concat("/credentialesaws.json");
		try {
			String content = new String(Files.readAllBytes(Paths.get(path)));
			JSONObject jsonObject = new JSONObject(content);

			this.setAccessKeyId(jsonObject.getString("ACCESS_KEY_ID"));
			this.setSecrectAccessId(jsonObject.getString("SECRET_ACCESS_KEY"));

		} catch (IOException e) {
			System.out.println("Error reading credentials file: " + e.getMessage());
		}
	}

	private StaticCredentialsProvider getStaticCredentialsProvider() {
		if (this.accessKeyId.isEmpty() || this.secrectAccessId.isEmpty()) {
			throw new IllegalArgumentException("Access Key ID and Secret Access Key cannot be blank.");
		}
		return StaticCredentialsProvider.create(
				AwsBasicCredentials.create(
						this.accessKeyId,
						this.secrectAccessId));
	}

	public SqsClient createSqsClient() {
		return SqsClient.builder()
				.region(Region.US_EAST_1)
				.credentialsProvider(getStaticCredentialsProvider())
				.build();
	}

	public SqsAsyncClient createSqsAsyncClient() {
		return SqsAsyncClient.builder()
				.region(Region.US_EAST_1)
				.credentialsProvider(getStaticCredentialsProvider())
				.build();
	}
}
