package br.com.guini.bpmspecification.spring;

import br.com.guini.bpmspecification.spring.service.PaymentCreateWorkflow;
import br.com.guini.bpmspecification.spring.service.SimpleWorkflow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BpmGeneratorTest {

    @Test
    void test() {

        final var bpmConfiguration = BpmConfiguration.config("X", PaymentCreateWorkflow.class, SimpleWorkflow.class);
        final var bpmGenerator = BpmGenerator.create(bpmConfiguration);
        final var workflowDTOList = bpmGenerator.getWorkflows();

        final var workflowDTO = workflowDTOList.get(0);
        assertEquals("Test", workflowDTO.getName());

    }
}
