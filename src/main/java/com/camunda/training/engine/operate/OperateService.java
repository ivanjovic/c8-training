package com.camunda.training.engine.operate;

import com.camunda.training.exception.OperateRuntimeException;
import com.camunda.training.exception.ProcessDefinitionNotDeployedException;
import io.camunda.operate.CamundaOperateClient;
import io.camunda.operate.dto.ProcessDefinition;
import io.camunda.operate.exception.OperateException;
import io.camunda.operate.search.ProcessDefinitionFilter;
import io.camunda.operate.search.SearchQuery;
import io.camunda.operate.search.Sort;
import io.camunda.operate.search.SortOrder;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperateService {

    private final CamundaOperateClient client;

    public ProcessDefinition getLastestProcessDefinition(String bpmnProcessId) {

        // try-catch needed because signature of build methods has a checked exception
        // remove when fixed within the lib, should only wrap the http call itself
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

            List<ProcessDefinition> processDefinitions = client.searchProcessDefinitions(processDefinitionFilter);

            // specify details for exception
            if (CollectionUtils.isEmpty(processDefinitions)) {
                throw new ProcessDefinitionNotDeployedException(bpmnProcessId);
            }

            return processDefinitions.get(0);

        } catch (OperateException ex) {
            throw new OperateRuntimeException(ex);
        }
    }
}
