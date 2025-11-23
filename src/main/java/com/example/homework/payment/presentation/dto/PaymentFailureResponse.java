package com.example.homework.payment.presentation.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentFailureResponse(
        UUID id,
        String orderId,
        String paymentKey,
        String errorCode,
        String errorMessage,
        Long amount,
        LocalDateTime createdAt
) {}
