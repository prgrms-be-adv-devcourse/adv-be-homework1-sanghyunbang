package com.example.homework.payment.infra.persistence;

import com.example.homework.payment.domain.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="\"payment\"", schema="public")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PaymentJpaEntity {

    @Id
    private UUID id;

    @Column(name="payment_key", nullable=false, unique=true, length=200)
    private String paymentKey;

    @Column(name="order_id", nullable=false, length=100)
    private String orderId;

    @Column(name="total_amount", nullable=false)
    private Long amount;

    @Column(name="method", length=50)
    private String method;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private PaymentStatus status;

    private LocalDateTime requestedAt;
    private LocalDateTime approvedAt;

    private String failReason;

    @Column(name="created_at", nullable=false)
    private LocalDateTime createdAt;

    @Column(name="updated_at", nullable=false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (id == null) id = UUID.randomUUID();
        if (status == null) status = PaymentStatus.READY;
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
