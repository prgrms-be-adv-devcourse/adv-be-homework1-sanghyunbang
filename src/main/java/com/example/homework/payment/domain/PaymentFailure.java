package com.example.homework.payment.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class PaymentFailure {

    private UUID id;
    private String orderId;
    private String paymentKey;
    private String errorCode;
    private String errorMessage;
    private Long amount;
    private String rawPayload;
    private LocalDateTime createdAt;

    private PaymentFailure(UUID id, String orderId, String paymentKey,
                           String errorCode, String errorMessage,
                           Long amount, String rawPayload, LocalDateTime createdAt) {
        this.id = id;
        this.orderId = orderId;
        this.paymentKey = paymentKey;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.amount = amount;
        this.rawPayload = rawPayload;
        this.createdAt = createdAt;
    }

    public static PaymentFailure create(String orderId, String paymentKey,
                                        String errorCode, String errorMessage,
                                        Long amount, String rawPayload) {
        return new PaymentFailure(
                UUID.randomUUID(),
                orderId,
                paymentKey,
                errorCode,
                errorMessage,
                amount,
                rawPayload,
                LocalDateTime.now()
        );
    }

    public static PaymentFailure restore(UUID id, String orderId, String paymentKey,
                                         String errorCode, String errorMessage,
                                         Long amount, String rawPayload, LocalDateTime createdAt) {
        return new PaymentFailure(id, orderId, paymentKey, errorCode, errorMessage, amount, rawPayload, createdAt);
    }
}
