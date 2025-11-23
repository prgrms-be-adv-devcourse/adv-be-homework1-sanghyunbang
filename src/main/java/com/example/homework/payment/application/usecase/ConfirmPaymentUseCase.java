package com.example.homework.payment.application.usecase;

import com.example.homework.payment.application.usecase.command.ConfirmPaymentCommand;
import com.example.homework.payment.domain.Payment;

public interface ConfirmPaymentUseCase {
    Payment confirm(ConfirmPaymentCommand command);
}
