package br.com.guini.bpmspecification.spring.service.steps;

import br.com.guini.bpmspecification.spring.annotation.BpmOperation;
import br.com.guini.bpmspecification.spring.domain.PaymentRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@BpmOperation(name = "Payment Validation")
public class PaymentValidationStep {

    public boolean validate(PaymentRequest paymentRequest) {
        return paymentRequest.getValue().compareTo(BigDecimal.TEN) > 0;
    }
}
