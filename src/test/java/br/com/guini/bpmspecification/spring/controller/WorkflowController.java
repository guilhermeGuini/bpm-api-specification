package br.com.guini.bpmspecification.spring.controller;

import br.com.guini.bpmspecification.spring.domain.PaymentRequest;
import br.com.guini.bpmspecification.spring.service.PaymentCreateWorkflow;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/workflow")
@AllArgsConstructor
public final class WorkflowController {

    private final PaymentCreateWorkflow paymentCreateWorkflow;

    @PostMapping("/payment")
    public void paymentCreateWorkflow(@Valid @RequestBody PaymentRequest paymentRequest) {
        paymentCreateWorkflow.start(paymentRequest);
    }
}
