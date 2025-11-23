package com.example.homework.payment.application.usecase.command;

public record ConfirmPaymentCommand(
        String paymentKey,
        String orderId,
        Long amount
) {}
