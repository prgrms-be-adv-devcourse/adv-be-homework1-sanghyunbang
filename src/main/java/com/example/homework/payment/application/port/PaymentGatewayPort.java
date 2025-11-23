package com.example.homework.payment.application.port;

import com.example.homework.payment.application.usecase.command.ConfirmPaymentCommand;
import com.example.homework.payment.infra.client.dto.TossConfirmResponse;

public interface PaymentGatewayPort {
    TossConfirmResponse confirm(ConfirmPaymentCommand command);
}
