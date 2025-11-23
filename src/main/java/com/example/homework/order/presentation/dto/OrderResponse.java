package com.example.homework.order.presentation.dto;

import com.example.homework.order.domain.PurchaseOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        UUID productId,
        UUID sellerId,
        UUID memberId,
        BigDecimal amount,
        PurchaseOrderStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
