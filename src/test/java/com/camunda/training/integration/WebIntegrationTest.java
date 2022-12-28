package com.camunda.training.integration;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
public class WebIntegrationTest {

    static final String LOCAL_HOST = "http://localhost:";

    @LocalServerPort
    int serverPort;

    @Value("wiremock.server.port")
    String wiremockPort;

    protected RequestSpecification given() {
        return RestAssured.given()
                .baseUri(getServiceUrl());
    }

    protected String getServiceUrl() {
        return LOCAL_HOST + serverPort;
    }

    protected String getWiremockUrl() {
        return LOCAL_HOST + wiremockPort;
    }
}
