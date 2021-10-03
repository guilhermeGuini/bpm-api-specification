package br.com.guini.bpmspecification.spring;

import br.com.guini.bpmspecification.spring.model.enums.WorkflowDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class BpmGenerator {

    private final BpmConfiguration bpmConfiguration;

    private BpmGenerator(BpmConfiguration bpmConfiguration) {
        Objects.requireNonNull(bpmConfiguration, "BpmConfiguration is required");
        this.bpmConfiguration = bpmConfiguration;
    }

    public static BpmGenerator create(BpmConfiguration bpmConfiguration) {
        return new BpmGenerator(bpmConfiguration);
    }


    public List<WorkflowDTO> test() {
        final List<WorkflowDTO> response = new ArrayList<>();

        final var workflows = bpmConfiguration.getWorkflows();

        for (Class workflow : workflows) {
            response.addAll(AnnotationExtractor.extract(workflow));
        }
        return response;
    }
}
