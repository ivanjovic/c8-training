package com.camunda.training.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.camunda.training.integration.stub.OperateStubProvider.stubProcessDefinitionSearch;
import static com.camunda.training.integration.stub.OperateStubProvider.stubToken;
import static com.camunda.training.testdata.OperateTestData.*;
import static org.hamcrest.Matchers.equalTo;

@ActiveProfiles("operate-integration")
public class OperateIT extends WebIntegrationTest {

    private static final String PATH = "/definition/{bpmnProcessId}/latest";

    @BeforeEach
    void stub() {
        stubToken();
    }

    @Test
    void whenResourceIsDeployedThenProcessDefinitionIsReturned() {

        stubProcessDefinitionSearch("test", List.of(defaultProcessDefinition()));

        given()
                .basePath(PATH)
                .pathParam("bpmnProcessId", BPMN_PROCESS_DEF)
                .when()
                .get()
                .then()
                .statusCode(200)
                .assertThat()
                .body(
                        "key", equalTo((int) PROCESS_DEF_KEY),
                        "version", equalTo((int) PROCESS_DEF_VERSION),
                        "name", equalTo(BPMN_PROCESS_DEF_NAME),
                        "bpmnProcessId", equalTo(BPMN_PROCESS_DEF)
                );
    }

    @Test
    void whenEmptyResultsetExceptionIsReturedAsProblemJson() {
        stubProcessDefinitionSearch("test", List.of());

        given()
                .basePath(PATH)
                .pathParam("bpmnProcessId", BPMN_PROCESS_DEF)
                .when()
                .get()
                .then()
                .statusCode(404)
                .contentType("application/problem+json")
                .assertThat()
                .body(
                        "type", equalTo("https://camunda.operate.com/not-found"),
                        "title", equalTo("Not found"),
                        "detail", equalTo("Procss-Definition 'test' not found")
                );
    }

}
