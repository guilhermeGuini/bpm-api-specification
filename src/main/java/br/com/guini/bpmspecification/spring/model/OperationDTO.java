package br.com.guini.bpmspecification.spring.model;

import br.com.guini.bpmspecification.spring.model.enums.BpmOperationType;

public class OperationDTO {

    private String name;

    private BpmOperationType type;

    private OperationDTO next;

    private OperationDTO nextUnsatisfiedCondition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BpmOperationType getType() {
        return type;
    }

    public void setType(BpmOperationType type) {
        this.type = type;
    }

    public OperationDTO getNext() {
        return next;
    }

    public void setNext(OperationDTO next) {
        this.next = next;
    }

    public OperationDTO getNextUnsatisfiedCondition() {
        return nextUnsatisfiedCondition;
    }

    public void setNextUnsatisfiedCondition(OperationDTO nextUnsatisfiedCondition) {
        this.nextUnsatisfiedCondition = nextUnsatisfiedCondition;
    }

    @Override
    public String toString() {
        return "\n    OperationDTO{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", next=" + next +
                ", nextUnsatisfiedCondition=" + nextUnsatisfiedCondition +
                '}';
    }
}
