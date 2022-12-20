package com.camunda.training.support;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.command.CompleteJobCommandStep1;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.protocol.Protocol;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ZeebeTestSupport {

    private final ZeebeClient zeebeClient;

    public String getVariable() {
        return null;
    }

    public void completeSendGridTask() {
        activateAndCompleteJob("io.camunda:sendgrid:1", Map.of());
    }

    public void completeUserTask(Map<String, Object> variables) {
        activateAndCompleteJob(Protocol.USER_TASK_JOB_TYPE, variables);
    }

    public void completeUserTask() {
        completeUserTask(Map.of());
    }

    public void activateAndCompleteJob(String jobType, Map<String, Object> variables) {
        completeJob(activateSingleJob(jobType), variables);
    }

    public void completeJob(ActivatedJob job, Map<String, Object> variables) {
        CompleteJobCommandStep1 completeJobCommandStep1 = zeebeClient.newCompleteCommand(job);

        if (variables != null && !variables.isEmpty()) {
            completeJobCommandStep1.variables(variables);
        }

        completeJobCommandStep1
                .send()
                .join();
    }

    public ActivatedJob activateSingleJob(String jobType) {
        var jobs = zeebeClient.newActivateJobsCommand()
                .jobType(jobType)
                .maxJobsToActivate(1)
                .send()
                .join()
                .getJobs();

        Assertions.assertThat(jobs)
                .hasSize(1);

        return jobs.get(0);
    }
}
