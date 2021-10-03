package br.com.guini.bpmspecification.spring;

import br.com.guini.bpmspecification.spring.model.enums.WorkflowDTO;

import java.util.List;

public class BpmApiSpecificationService {

    private final BpmGenerator generator;

    public BpmApiSpecificationService(BpmGenerator generator) {
        this.generator = generator;
    }

    public List<WorkflowDTO> getWorkflow() {
        return generator.test();
    }
}
