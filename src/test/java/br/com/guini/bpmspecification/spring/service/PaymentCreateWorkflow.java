package br.com.guini.bpmspecification.spring.service;

import br.com.guini.bpmspecification.spring.annotation.BpmConditionOperation;
import br.com.guini.bpmspecification.spring.annotation.BpmOperation;
import br.com.guini.bpmspecification.spring.annotation.BpmWorkflow;
import br.com.guini.bpmspecification.spring.domain.PaymentRequest;
import br.com.guini.bpmspecification.spring.model.enums.BpmOperationType;
import br.com.guini.bpmspecification.spring.service.steps.NotificationStep;
import br.com.guini.bpmspecification.spring.service.steps.PaymentCreationStep;
import br.com.guini.bpmspecification.spring.service.steps.PaymentValidationStep;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@BpmWorkflow(value = {
        @BpmOperation(name = "Payment Validation Step"),
        @BpmOperation(name = "Validation OK", type = BpmOperationType.CONDITION)
}, satisfiedCondition = {
        @BpmConditionOperation(value = @BpmOperation(name = "Create"),
                parent = @BpmOperation(name = "Validation OK", type = BpmOperationType.CONDITION))
}, unsatisfiedCondition = {
        @BpmConditionOperation(value = @BpmOperation(name = "Notify"),
                parent = @BpmOperation(name = "Validation OK", type = BpmOperationType.CONDITION))
}, name = "Test")
@Service
@AllArgsConstructor
public final class PaymentCreateWorkflow {

    private final PaymentCreationStep paymentCreationStep;
    private final PaymentValidationStep paymentValidationStep;
    private final NotificationStep notificationStep;

    public void start(PaymentRequest paymentRequest) {

        if (!paymentValidationStep.validate(paymentRequest)) {
            notificationStep.notify(paymentRequest.getDrawerName(), paymentRequest.getDrawerEmail(), "Fail payment validation");
            return;
        }

        paymentCreationStep.createPayment(paymentRequest);
    }

}
