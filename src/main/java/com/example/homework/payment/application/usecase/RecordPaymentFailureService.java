package com.example.homework.payment.application.usecase;

import com.example.homework.payment.application.port.PaymentFailureRepositoryPort;
import com.example.homework.payment.application.usecase.command.RecordPaymentFailCommand;
import com.example.homework.payment.domain.PaymentFailure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordPaymentFailureService implements RecordPaymentFailureUseCase {

    private final PaymentFailureRepositoryPort failureRepository;

    @Override
    public PaymentFailure recordFail(RecordPaymentFailCommand command) {

        PaymentFailure failure = PaymentFailure.create(
                command.orderId(),
                command.paymentKey(),
                command.errorCode(),
                command.errorMessage(),
                command.amount(),
                command.rawPayload()
        );

        return failureRepository.save(failure);
    }
}
