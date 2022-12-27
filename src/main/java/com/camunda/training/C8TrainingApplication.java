package com.camunda.training;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@EnableZeebeClient
public class C8TrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(C8TrainingApplication.class, args);
	}

}
