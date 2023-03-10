package com.camunda.training;

public class TestConstants {

    public static String START_EVENT_ID = "StartEvent_1";
    public static String VALIDATE_INPUT_SERVICE_TASK = "VALIDATE_INPUT_SERVICE_TASK";
    public static String DRY_RUN_GW = "DRY_RUN_GW";
    public static String EVALUATE_CONSOLE_BUSINESS_RULE_TASK = "EVALUATE_CONSOLE_BUSINESS_RULE_TASK";
    public static String UNIQUE_RESULT_GW = "UNIQUE_RESULT_GW";
    public static String SEND_CONSOLE_SUGGESTION_SENDGRID_CONNECTOR = "SEND_CONSOLE_SUGGESTION_SENDGRID_CONNECTOR";
    public static String ASSIGN_CONSOLE_USER_TASK = "ASSIGN_CONSOLE_USER_TASK";
    public static String SEND_REJECTION_SENDGRID_CONNECTOR = "SEND_REJECTION_SENDGRID_CONNECTOR";
    public static String CONSOLE_SUGGESTED_END_EVENT = "CONSOLE_SUGGESTED_END_EVENT";
    public static String NO_SUGGESTION_POSSIBLE_END_EVENT = "NO_SUGGESTION_POSSIBLE_END_EVENT";

    public static String[] HAPPY_PATH = {
            START_EVENT_ID,
            VALIDATE_INPUT_SERVICE_TASK,
            DRY_RUN_GW,
            EVALUATE_CONSOLE_BUSINESS_RULE_TASK,
            UNIQUE_RESULT_GW,
            SEND_CONSOLE_SUGGESTION_SENDGRID_CONNECTOR,
            CONSOLE_SUGGESTED_END_EVENT
    };

    public static String[] MULTIPLE_SUGGESTIONS_PATH = {
            START_EVENT_ID,
            VALIDATE_INPUT_SERVICE_TASK,
            DRY_RUN_GW,
            EVALUATE_CONSOLE_BUSINESS_RULE_TASK,
            UNIQUE_RESULT_GW,
            ASSIGN_CONSOLE_USER_TASK,
            SEND_CONSOLE_SUGGESTION_SENDGRID_CONNECTOR,
            CONSOLE_SUGGESTED_END_EVENT
    };

    public static String[] NO_SUGGESTIONS_PATH = {
            START_EVENT_ID,
            VALIDATE_INPUT_SERVICE_TASK,
            DRY_RUN_GW,
            EVALUATE_CONSOLE_BUSINESS_RULE_TASK,
            UNIQUE_RESULT_GW,
            SEND_REJECTION_SENDGRID_CONNECTOR,
            NO_SUGGESTION_POSSIBLE_END_EVENT
    };
}
