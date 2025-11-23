package com.example.homework.order.application.usecase.command;

import com.example.homework.order.domain.PurchaseOrderStatus;

public record ChangeOrderStatusCommand (
        String orderId,
        PurchaseOrderStatus status
) {}
