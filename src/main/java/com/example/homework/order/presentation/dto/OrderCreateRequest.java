package com.example.homework.order.presentation.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class OrderCreateRequest {
    private UUID productId;
    private UUID sellerId;
    private UUID memberId;
    private BigDecimal amount;
}
