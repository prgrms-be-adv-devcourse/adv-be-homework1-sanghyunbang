package com.example.homework.order.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class PurchaseOrder {

    private UUID id;
    private UUID productId;
    private UUID sellerId;
    private UUID memberId;
    private BigDecimal amount;
    private PurchaseOrderStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // "도메인 생성 규칙"을 담은 생성자
    private PurchaseOrder(UUID id, UUID productId, UUID sellerId, UUID memberId,
                          BigDecimal amount, PurchaseOrderStatus status,
                          LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.productId = productId;
        this.sellerId = sellerId;
        this.memberId = memberId;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // 주문 생성은 도메인 규칙으로 정의
    public static PurchaseOrder create(UUID productId, UUID sellerId, UUID memberId, BigDecimal amount) {
        LocalDateTime now = LocalDateTime.now();
        return new PurchaseOrder(
                UUID.randomUUID(),
                productId,
                sellerId,
                memberId,
                amount,
                PurchaseOrderStatus.CREATED,
                now,
                now
        );
    }

    // 저장된 데이터를 도메인 객체로 복원할 때 쓰는 용도
    public static PurchaseOrder restore(
            UUID id, UUID productId, UUID sellerId, UUID memberId,
            BigDecimal amount, PurchaseOrderStatus status,
            LocalDateTime createdAt, LocalDateTime updatedAt
    ) {
        return new PurchaseOrder(id, productId, sellerId, memberId, amount, status, createdAt, updatedAt);
    }


    // 비즈니스 행위
    public void markPaid() {
        if (this.status != PurchaseOrderStatus.CREATED) {
            throw new IllegalStateException("Only CREATED orders can be paid.");
        }
        this.status = PurchaseOrderStatus.PAID;
        this.updatedAt = LocalDateTime.now();
    }

    public void cancel() {
        if (this.status == PurchaseOrderStatus.PAID) {
            throw new IllegalStateException("Paid orders cannot be cancelled.");
        }
        this.status = PurchaseOrderStatus.CANCELLED;
        this.updatedAt = LocalDateTime.now();
    }
}