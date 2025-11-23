package com.example.homework.payment.application.usecase;

import com.example.homework.payment.application.usecase.command.RecordPaymentFailCommand;
import com.example.homework.payment.domain.PaymentFailure;

public interface RecordPaymentFailureUseCase {
    PaymentFailure recordFail(RecordPaymentFailCommand command);
}
