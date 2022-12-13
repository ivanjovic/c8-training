package com.camunda.training.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RangeTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void isInRange(int i) {
        Assertions.assertThat(getTestRange().contains(i)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {-4, 0, 11, 100})
    void isNotInRage(int i) {
        Assertions.assertThat(getTestRange().contains(i)).isFalse();
    }

    private Range getTestRange() {
        return new Range(1, 10);
    }
}
