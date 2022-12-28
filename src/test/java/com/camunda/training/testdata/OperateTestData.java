package com.camunda.training.testdata;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.camunda.operate.dto.ProcessDefinition;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public class OperateTestData {

    public static final long PROCESS_DEF_KEY = 100;
    public static final long PROCESS_DEF_VERSION = 1;
    public static final String BPMN_PROCESS_DEF = "test";
    public static final String BPMN_PROCESS_DEF_NAME = "Test name";

    public static final String JWT_ISSUER = "https://operate-integrationtest.com/";
    public static final String JWT_SECRET = "jwtSecret";

    public static ProcessDefinition defaultProcessDefinition() {
        ProcessDefinition d = new ProcessDefinition();

        d.setKey(PROCESS_DEF_KEY);
        d.setVersion(PROCESS_DEF_VERSION);
        d.setName(BPMN_PROCESS_DEF_NAME);
        d.setBpmnProcessId(BPMN_PROCESS_DEF);

        return d;
    }

    public static Map<String, Object> defaultTokenResponse() {
        return Map.of(
                "access_token", defaultOperateJwt(),
                "scope", "scope",
                "expires_in", 86400,
                "token_type", "Bearer"
        );
    }

    public static String defaultOperateJwt() {

        Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);

        return JWT.create()
                .withIssuer(JWT_ISSUER)
                .withSubject("subject")
                .withAudience("operate")
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plus(1L, ChronoUnit.DAYS))
                .sign(algorithm);
    }
}
