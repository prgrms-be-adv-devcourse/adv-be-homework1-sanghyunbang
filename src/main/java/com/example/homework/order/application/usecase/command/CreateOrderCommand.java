package com.example.homework.order.application.usecase.command;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateOrderCommand (
        UUID productId,
        UUID sellerId,
        UUID memberId,
        BigDecimal amount
) {}


