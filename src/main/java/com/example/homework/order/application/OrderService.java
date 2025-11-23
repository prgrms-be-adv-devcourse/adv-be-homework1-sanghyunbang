package com.example.homework.order.application;



import com.example.homework.order.application.port.OrderRepositoryPort;
import com.example.homework.order.application.usecase.ChangeOrderStatusUseCase;
import com.example.homework.order.application.usecase.CreateOrderUseCase;
import com.example.homework.order.application.usecase.GetOrdersUseCase;
import com.example.homework.order.application.usecase.command.ChangeOrderStatusCommand;
import com.example.homework.order.application.usecase.command.CreateOrderCommand;
import com.example.homework.order.application.usecase.command.GetOrdersQuery;
import com.example.homework.order.domain.PurchaseOrder;
import com.example.homework.order.domain.PurchaseOrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService implements CreateOrderUseCase, GetOrdersUseCase, ChangeOrderStatusUseCase {

    private final OrderRepositoryPort orderRepository;

    @Override
    public PurchaseOrder create(CreateOrderCommand command){

        UUID sellerId = command.sellerId() != null
                ? command.sellerId()
                : UUID.randomUUID(); // 테스트용 기본값

        var amount = command.amount() != null
                ? command.amount()
                : java.math.BigDecimal.valueOf(1000); // 또는 BigDecimal.ONE

        PurchaseOrder order = PurchaseOrder.create(
                command.productId(),
                sellerId,
                command.memberId(),
                amount
        );

        return orderRepository.save(order);
    }

    @Override
    public List<PurchaseOrder> getOrders(GetOrdersQuery query){
        return orderRepository.findAll(query.page(), query.size());
    }

    @Override
    public long getTotalCount(){
        return orderRepository.count();
    }

    @Override
    public PurchaseOrder changeStatus(ChangeOrderStatusCommand command){
        PurchaseOrder order = orderRepository.findById(UUID.fromString(command.orderId()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID: " + command.orderId()));

        if(command.status() == PurchaseOrderStatus.PAID) order.markPaid();
        if(command.status() == PurchaseOrderStatus.CANCELLED) order.cancel();

        return orderRepository.save(order);
    }

}
