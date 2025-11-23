package com.example.homework.order.presentation.mapper;

import com.example.homework.order.application.usecase.command.ChangeOrderStatusCommand;
import com.example.homework.order.application.usecase.command.CreateOrderCommand;
import com.example.homework.order.application.usecase.command.GetOrdersQuery;
import com.example.homework.order.domain.PurchaseOrderStatus;
import com.example.homework.order.presentation.dto.OrderCreateRequest;
import org.springframework.stereotype.Component;

// request -> UseCase 인풋
@Component
public class OrderCommandMapper {

    public CreateOrderCommand toCreateCommand(OrderCreateRequest req) {
        return new CreateOrderCommand(
                req.getProductId(),
                req.getSellerId(),
                req.getMemberId(),
                req.getAmount()
        );
    }

    public ChangeOrderStatusCommand toChangeStatusCommand(String id, PurchaseOrderStatus status){
        return new ChangeOrderStatusCommand(id, status);
    }

    public GetOrdersQuery toGetOrdersQuery(int page, int size){
        return new GetOrdersQuery(page, size);
    }
}
