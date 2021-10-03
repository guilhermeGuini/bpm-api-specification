package br.com.guini.bpmspecification.spring;

import br.com.guini.bpmspecification.spring.utils.ValidationUtils;

public class BpmConfiguration {

    private Class[] workflows;

    private String name;

    private BpmConfiguration(Class[] workflows, String name) {
        this.workflows = workflows;
        this.name = name;
    }

    public Class[] getWorkflows() {
        return workflows;
    }

    public String getName() {
        return name;
    }

    public static BpmConfiguration config(String name, Class... workflows) {
        ValidationUtils.NotEmpty(workflows);
        ValidationUtils.NotBlank(name);
        return new BpmConfiguration(workflows, name);
    }

}
