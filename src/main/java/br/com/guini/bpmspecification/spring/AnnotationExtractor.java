package br.com.guini.bpmspecification.spring;

import br.com.guini.bpmspecification.spring.annotation.BpmConditionOperation;
import br.com.guini.bpmspecification.spring.annotation.BpmOperation;
import br.com.guini.bpmspecification.spring.annotation.BpmWorkflow;
import br.com.guini.bpmspecification.spring.model.OperationDTO;
import br.com.guini.bpmspecification.spring.model.WorkflowDTO;

import java.util.ArrayList;
import java.util.List;

final class AnnotationExtractor {

    private AnnotationExtractor() {
    }

    static List<WorkflowDTO> extract(Class clazz) {

        final var bpmWorkflows = (BpmWorkflow[]) clazz.getAnnotationsByType(BpmWorkflow.class);

        final List<WorkflowDTO> response = new ArrayList<>();

        for (BpmWorkflow workflow : bpmWorkflows) {

            final var workflowDTO = new WorkflowDTO();
            workflowDTO.setName(workflow.name());
            workflowDTO.setOperation(extractOperations(workflow));
            response.add(workflowDTO);
        }

        return response;
    }

    private static OperationDTO extractOperations(BpmWorkflow workflow) {

        final var operationDTO = extractNormalOperations(workflow.value(), 0);

        extractSatisfiedCondition(workflow.satisfiedCondition(), operationDTO, workflow);
        extractUnsatisfiedCondition(workflow.unsatisfiedCondition(), operationDTO, workflow);

        return operationDTO;
    }

    private static void extractUnsatisfiedCondition(BpmConditionOperation[] unsatisfiedCondition, OperationDTO operationDTO, BpmWorkflow workflow) {
        for (BpmConditionOperation conditionOperation : unsatisfiedCondition) {
            final var parent = getOperationParent(conditionOperation.parent(), operationDTO);
            parent.setNextUnsatisfiedCondition(extractNormalOperations(conditionOperation.value(), 0));
        }
    }

    private static void extractSatisfiedCondition(BpmConditionOperation[] satisfiedCondition, OperationDTO operationDTO, BpmWorkflow workflow) {
        for (BpmConditionOperation conditionOperation : satisfiedCondition) {
            final var parent = getOperationParent(conditionOperation.parent(), operationDTO);
            parent.setNext(extractNormalOperations(conditionOperation.value(), 0));
        }
    }

    private static OperationDTO getOperationParent(BpmOperation parent, OperationDTO operationDTO) {

        if (operationDTO == null) {
            throw new RuntimeException("Operation parent not found");
        }

        if (parent.name().equals(operationDTO.getName())) {
            return operationDTO;
        }

        return getOperationParent(parent, operationDTO.getNext());
    }

    private static OperationDTO extractNormalOperations(BpmOperation[] operations, int index) {

        if (index == operations.length) {
            return null;
        }

        OperationDTO dto = new OperationDTO();

        dto.setType(operations[index].type());
        dto.setName(operations[index].name());
        dto.setNext(extractNormalOperations(operations, index + 1));

        return dto;
    }

}
