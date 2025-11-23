package com.example.homework.payment.infra.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="\"payment_failure\"", schema="public")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PaymentFailureJpaEntity {

    @Id
    private UUID id;

    @Column(nullable=false, length=100)
    private String orderId;

    @Column(length=200)
    private String paymentKey;

    @Column(length=50)
    private String errorCode;

    private String errorMessage;
    private Long amount;

    @Lob
    private String rawPayload;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        if (id == null) id = UUID.randomUUID();
        createdAt = LocalDateTime.now();
    }
}
