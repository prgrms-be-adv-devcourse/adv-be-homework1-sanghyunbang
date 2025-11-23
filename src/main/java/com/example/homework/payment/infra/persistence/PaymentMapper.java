package com.example.homework.payment.infra.persistence;

import com.example.homework.payment.domain.Payment;

public class PaymentMapper {

    private PaymentMapper() {}

    public static PaymentJpaEntity toJpa(Payment d) {
        return PaymentJpaEntity.builder()
                .id(d.getId())
                .paymentKey(d.getPaymentKey())
                .orderId(d.getOrderId())
                .amount(d.getAmount())
                .method(d.getMethod())
                .status(d.getStatus())
                .requestedAt(d.getRequestedAt())
                .approvedAt(d.getApprovedAt())
                .failReason(d.getFailReason())
                .createdAt(d.getCreatedAt())
                .updatedAt(d.getUpdatedAt())
                .build();
    }

    public static Payment toDomain(PaymentJpaEntity e) {
        return Payment.restore(
                e.getId(),
                e.getPaymentKey(),
                e.getOrderId(),
                e.getAmount(),
                e.getMethod(),
                e.getStatus(),
                e.getRequestedAt(),
                e.getApprovedAt(),
                e.getFailReason(),
                e.getCreatedAt(),
                e.getUpdatedAt()
        );
    }
}
