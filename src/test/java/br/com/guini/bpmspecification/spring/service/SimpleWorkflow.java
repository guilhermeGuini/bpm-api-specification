package br.com.guini.bpmspecification.spring.service;

import br.com.guini.bpmspecification.spring.annotation.BpmOperation;
import br.com.guini.bpmspecification.spring.annotation.BpmWorkflow;
import org.springframework.stereotype.Service;

@BpmWorkflow(name = "Simple Workflow", value = {@BpmOperation(name = "Hello World")})
@Service
public class SimpleWorkflow {

    public void execute() {
        System.out.println("Hello World");
    }
}
