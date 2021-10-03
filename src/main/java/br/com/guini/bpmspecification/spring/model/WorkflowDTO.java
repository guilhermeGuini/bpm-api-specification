package br.com.guini.bpmspecification.spring.model;

public class WorkflowDTO {

    private String name;

    private OperationDTO operation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OperationDTO getOperation() {
        return operation;
    }

    public void setOperation(OperationDTO operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "WorkflowDTO{" +
                "name='" + name + '\'' +
                ", operation=" + operation +
                "}\n";
    }
}
