package com.example.homework.order.application.usecase;

import com.example.homework.order.application.usecase.command.GetOrdersQuery;
import com.example.homework.order.domain.PurchaseOrder;

import java.util.List;

public interface GetOrdersUseCase {
    List<PurchaseOrder> getOrders(GetOrdersQuery query);
    long getTotalCount();
}
