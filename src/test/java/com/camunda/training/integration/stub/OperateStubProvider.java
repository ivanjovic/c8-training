package com.camunda.training.integration.stub;

import io.camunda.operate.dto.ProcessDefinition;
import io.camunda.operate.dto.SearchResult;
import io.camunda.operate.search.ProcessDefinitionFilter;
import io.camunda.operate.search.SearchQuery;
import io.camunda.operate.search.Sort;
import io.camunda.operate.search.SortOrder;
import io.camunda.operate.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.camunda.training.testdata.OperateTestData.defaultTokenResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Slf4j
public class OperateStubProvider {

    public static final String BASE_PATH = "/operate";
    public static final String TOKEN_PATH = BASE_PATH + "/oauth/token";
    public static final String PROCESS_DEF_PATH = BASE_PATH + "/v1/process-definitions";
    public static final String SEARCH_SUFFIX = "/search";

    public static void stubToken() {
        stubFor(
                post(urlPathEqualTo(TOKEN_PATH))
                        .withRequestBody(
                                equalToJson("{\"client_id\": \"client-id\",\"client_secret\": \"secret\",\"audience\": \"operate\",\"grant_type\": \"client_credentials\"}")
                        )
                        .willReturn(
                                jsonResponse(defaultTokenResponse(), 200)
                        )
        );
    }

    public static void stubProcessDefinitionSearch(String bpmnProcessId, List<ProcessDefinition> response) {
        try {
            SearchQuery processDefinitionFilter = new SearchQuery.Builder()
                    .withFilter(
                            new ProcessDefinitionFilter.Builder()
                                    .bpmnProcessId(bpmnProcessId)
                                    .build()
                    )
                    .withSize(1)
                    .withSort(
                            new Sort("version", SortOrder.DESC)
                    )
                    .build();

            var result = new SearchResult<ProcessDefinition>();
            result.setItems(response);
            result.setTotal(response.size());

            stubFor(
                    post(urlPathEqualTo(PROCESS_DEF_PATH + SEARCH_SUFFIX))
                            .withRequestBody(
                                    equalToJson(JsonUtils.toJson(processDefinitionFilter))
                            )
                            .willReturn(
                                    jsonResponse(
                                            JsonUtils.toJson(result), 200
                                    )
                            )
            );

        } catch (Exception ex) {
            log.error("Error during stub creating for stubProcessDefinitionSearch", ex);
        }

    }
}
