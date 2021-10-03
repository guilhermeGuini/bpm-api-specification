package br.com.guini.bpmspecification.spring.controller;

import br.com.guini.bpmspecification.spring.BpmApiSpecificationService;
import br.com.guini.bpmspecification.spring.BpmConfiguration;
import br.com.guini.bpmspecification.spring.BpmGenerator;
import br.com.guini.bpmspecification.spring.model.enums.WorkflowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/bpm")
@RestController
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BpmApiSpecificationController {

    private final BpmApiSpecificationService service;

    @Autowired
    public BpmApiSpecificationController(BpmConfiguration configuration) {
        this.service = new BpmApiSpecificationService(BpmGenerator.create(configuration));
    }

    @GetMapping("/workflow")
    public List<WorkflowDTO> getWorkflow() {
        return service.getWorkflow();
    }
}
