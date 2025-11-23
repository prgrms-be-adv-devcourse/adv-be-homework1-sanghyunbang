package com.example.homework.payment.infra.persistence;

import com.example.homework.payment.domain.PaymentFailure;

public class PaymentFailureMapper {

    private PaymentFailureMapper() {}

    public static PaymentFailureJpaEntity toJpa(PaymentFailure d) {
        return PaymentFailureJpaEntity.builder()
                .id(d.getId())
                .orderId(d.getOrderId())
                .paymentKey(d.getPaymentKey())
                .errorCode(d.getErrorCode())
                .errorMessage(d.getErrorMessage())
                .amount(d.getAmount())
                .rawPayload(d.getRawPayload())
                .createdAt(d.getCreatedAt())
                .build();
    }

    public static PaymentFailure toDomain(PaymentFailureJpaEntity e) {
        return PaymentFailure.restore(
                e.getId(),
                e.getOrderId(),
                e.getPaymentKey(),
                e.getErrorCode(),
                e.getErrorMessage(),
                e.getAmount(),
                e.getRawPayload(),
                e.getCreatedAt()
        );
    }
}
