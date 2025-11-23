package com.example.homework.payment.application.usecase.command;

public record RecordPaymentFailCommand(
        String orderId,
        String paymentKey,
        String errorCode,
        String errorMessage,
        Long amount,
        String rawPayload
) {}
