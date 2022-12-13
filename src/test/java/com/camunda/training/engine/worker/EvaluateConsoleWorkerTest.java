package com.camunda.training.engine.worker;

import com.camunda.training.engine.variables.InputData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Map;

import static com.camunda.training.config.ProcessConstants.VK_SUGGESTIONS;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class EvaluateConsoleWorkerTest {

    @InjectMocks
    EvaluateConsoleWorker worker;

    @ParameterizedTest
    @ValueSource(ints = {1972, 1981, Year.MAX_VALUE})
    void returnsOnlyOneSuggestionForNonOverlappingYears(int year) {

        Map<String, List<String>> result = worker.evaluateConsole(getInputData(year));

        assertThat(result.get(VK_SUGGESTIONS))
                .hasSize(1)
                .containsAnyOf(
                        "Magnavox Odyssey",
                        "Atari 2600",
                        "Playstation 5"
                );
    }

    @Test
    void returnsAllConsoleMatchingYearRange() {
        Map<String, List<String>> result = worker.evaluateConsole(getInputData(2005));

        assertThat(result.get(VK_SUGGESTIONS))
                .hasSize(3)
                .containsOnly(
                        "Sony Playstation 1",
                        "Sony Playstation 2",
                        "Nintendo Wii"
                );
    }

    private InputData getInputData(int year) {
        return InputData.builder()
                .email("test@camunda.com")
                .name("Camunda")
                .birthday(LocalDate.of(year, 1, 1))
                .build();
    }
}
