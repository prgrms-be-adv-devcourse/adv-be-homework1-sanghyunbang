package com.example.homework.payment.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Payment {

    private UUID id;
    private String paymentKey;
    private String orderId;
    private Long amount;
    private String method;
    private PaymentStatus status;
    private LocalDateTime requestedAt;
    private LocalDateTime approvedAt;
    private String failReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Payment(UUID id, String paymentKey, String orderId, Long amount,
                    String method, PaymentStatus status,
                    LocalDateTime requestedAt, LocalDateTime approvedAt,
                    String failReason, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.requestedAt = requestedAt;
        this.approvedAt = approvedAt;
        this.failReason = failReason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /** 결제 준비 상태로 생성 */
    public static Payment ready(String paymentKey, String orderId, Long amount) {
        LocalDateTime now = LocalDateTime.now();
        return new Payment(
                UUID.randomUUID(),
                paymentKey,
                orderId,
                amount,
                null,
                PaymentStatus.READY,
                null,
                null,
                null,
                now,
                now
        );
    }

    /** DB에서 복원 */
    public static Payment restore(UUID id, String paymentKey, String orderId, Long amount,
                                  String method, PaymentStatus status,
                                  LocalDateTime requestedAt, LocalDateTime approvedAt,
                                  String failReason, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new Payment(id, paymentKey, orderId, amount, method, status,
                requestedAt, approvedAt, failReason, createdAt, updatedAt);
    }

    /** 결제 승인(확정) */
    public void confirm(String method, LocalDateTime approvedAt, LocalDateTime requestedAt) {
        if (this.status != PaymentStatus.READY) {
            throw new IllegalStateException("Only READY payments can be confirmed.");
        }
        this.status = PaymentStatus.CONFIRMED;
        this.method = method;
        this.approvedAt = approvedAt;
        this.requestedAt = requestedAt;
        this.failReason = null;
        this.updatedAt = LocalDateTime.now();
    }

    /** 결제 실패 */
    public void fail(String reason) {
        this.status = PaymentStatus.FAILED;
        this.failReason = reason;
        this.updatedAt = LocalDateTime.now();
    }
}
