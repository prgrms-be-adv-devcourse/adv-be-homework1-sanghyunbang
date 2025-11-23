package com.example.homework.payment.presentation.dto;

public record PaymentFailRequest(
        String orderId,
        String paymentKey,
        String code,
        String message,
        Long amount,
        String rawPayload
) {}
