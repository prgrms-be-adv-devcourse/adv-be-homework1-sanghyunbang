package com.example.homework.order.presentation.mapper;

import com.example.homework.order.domain.PurchaseOrder;
import com.example.homework.order.presentation.dto.OrderResponse;

//admin -> response
public class OrderResponseMapper {

    private OrderResponseMapper(){}

    public static OrderResponse toResponse(PurchaseOrder o) {
        return new OrderResponse(
                o.getId(),
                o.getProductId(),
                o.getSellerId(),
                o.getMemberId(),
                o.getAmount(),
                o.getStatus(),
                o.getCreatedAt(),
                o.getUpdatedAt()
        );
    }
}
