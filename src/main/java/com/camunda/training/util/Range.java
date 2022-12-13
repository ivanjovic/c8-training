package com.camunda.training.util;

public record Range(int min, int max) {

    public boolean contains(int num) {
        return (num >= min && num <= max);
    }
}
