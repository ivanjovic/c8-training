package com.camunda.training.integration;

import com.camunda.training.ZeebeIntegrationTest;
import com.camunda.training.controller.dto.StartProcessRequest;
import com.camunda.training.service.ProcessStarter;
import com.camunda.training.support.ZeebeTestSupport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Map;

import static com.camunda.training.TestConstants.*;
import static io.camunda.zeebe.process.test.assertions.BpmnAssert.assertThat;
import static io.camunda.zeebe.spring.test.ZeebeTestThreadSupport.waitForProcessInstanceCompleted;
import static io.camunda.zeebe.spring.test.ZeebeTestThreadSupport.waitForProcessInstanceHasPassedElement;

public class ConsoleSuggestionProcessIT extends ZeebeIntegrationTest {

    @Autowired
    private ProcessStarter processStarter;

    @Autowired
    private ZeebeTestSupport testSupport;

    @Test
    void whenUniqueConsoleResultThenProcessFinishedWithHappyPath() {
        var instanceEvent = processStarter.startConsoleSuggestionProcess(request(1981), false);

        waitForProcessInstanceHasPassedElement(instanceEvent, EVALUATE_CONSOLE_BUSINESS_RULE_TASK);

        assertThat(instanceEvent)
                .isWaitingAtElements(SEND_CONSOLE_SUGGESTION_SENDGRID_CONNECTOR)
                .hasVariable("suggestionList");

        testSupport.completeSendGridTask();

        waitForProcessInstanceCompleted(instanceEvent);

        assertThat(instanceEvent)
                .isCompleted()
                .hasPassedElementsInOrder(HAPPY_PATH);
    }

    @Test
    void whenMultipleConsoleResultsThenProcessRunsThroughUserTask() {
        var instanceEvent = processStarter.startConsoleSuggestionProcess(request(2005), false);

        waitForProcessInstanceHasPassedElement(instanceEvent, EVALUATE_CONSOLE_BUSINESS_RULE_TASK);

        assertThat(instanceEvent)
                .isWaitingAtElements(ASSIGN_CONSOLE_USER_TASK)
                .hasVariable("suggestionList");

        testSupport.completeUserTask(Map.of("selectedSuggestion", "Sony Playstation 2"));

        waitForProcessInstanceHasPassedElement(instanceEvent, ASSIGN_CONSOLE_USER_TASK);

        assertThat(instanceEvent)
                .isWaitingAtElements(SEND_CONSOLE_SUGGESTION_SENDGRID_CONNECTOR)
                .hasVariableWithValue("selectedSuggestion", "Sony Playstation 2");

        testSupport.completeSendGridTask();

        waitForProcessInstanceCompleted(instanceEvent);

        assertThat(instanceEvent)
                .isCompleted()
                .hasPassedElementsInOrder(MULTIPLE_SUGGESTIONS_PATH);
    }

    @Test
    void whenNoConsoleResultsThenRejectionMailIsSent() {
        var instanceEvent = processStarter.startConsoleSuggestionProcess(request(1950), false);

        waitForProcessInstanceHasPassedElement(instanceEvent, EVALUATE_CONSOLE_BUSINESS_RULE_TASK);

        assertThat(instanceEvent)
                .isWaitingAtElements(SEND_REJECTION_SENDGRID_CONNECTOR)
                .hasVariable("suggestionList");

        testSupport.completeSendGridTask();

        waitForProcessInstanceCompleted(instanceEvent);

        assertThat(instanceEvent)
                .isCompleted()
                .hasPassedElementsInOrder(NO_SUGGESTIONS_PATH);
    }

    private StartProcessRequest request(int year) {
        return StartProcessRequest.builder()
                .name("Zeebe Integrationtest")
                .email("test@camunda.com")
                .birthday(LocalDate.of(year, 1, 1))
                .build();
    }
}
