package br.com.guini.bpmspecification.spring;

import br.com.guini.bpmspecification.spring.model.enums.WorkflowDTO;
import br.com.guini.bpmspecification.spring.service.PaymentCreateWorkflow;
import br.com.guini.bpmspecification.spring.service.SimpleWorkflow;
import org.junit.jupiter.api.Test;

import java.util.List;

class BpmGeneratorTest {

    @Test
    void test() {

        final var bpmConfiguration = BpmConfiguration.config("X", PaymentCreateWorkflow.class, SimpleWorkflow.class);

        final var bpmGenerator = BpmGenerator.create(bpmConfiguration);

        final var workflowDTOList = bpmGenerator.test();

        System.out.println(workflowDTOList);
    }
}
