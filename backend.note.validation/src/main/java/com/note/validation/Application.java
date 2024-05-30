package com.note.validation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.note")
@EnableJpaRepositories(basePackages = "com.note.validation.domain.repository")
@EntityScan(basePackages = "com.note.persistence.entitys")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
