package com.example.homework.payment.presentation.mapper;

import com.example.homework.payment.application.usecase.command.ConfirmPaymentCommand;
import com.example.homework.payment.application.usecase.command.RecordPaymentFailCommand;
import com.example.homework.payment.presentation.dto.PaymentConfirmRequest;
import com.example.homework.payment.presentation.dto.PaymentFailRequest;
import org.springframework.stereotype.Component;

@Component
public class PaymentCommandMapper {

    public ConfirmPaymentCommand toConfirmCommand(PaymentConfirmRequest r) {
        return new ConfirmPaymentCommand(r.paymentKey(), r.orderId(), r.amount());
    }

    public RecordPaymentFailCommand toFailCommand(PaymentFailRequest r) {
        return new RecordPaymentFailCommand(
                r.orderId(), r.paymentKey(), r.code(), r.message(), r.amount(), r.rawPayload()
        );
    }
}
