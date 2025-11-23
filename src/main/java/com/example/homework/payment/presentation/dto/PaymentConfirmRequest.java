package com.example.homework.payment.presentation.dto;

public record PaymentConfirmRequest(
        String paymentKey,
        String orderId,
        Long amount
) {}
