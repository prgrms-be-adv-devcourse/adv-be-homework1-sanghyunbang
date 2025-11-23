// payment/infra/order/OrderPaymentAdapter.java
package com.example.homework.payment.infra.order;

import com.example.homework.order.application.port.OrderRepositoryPort;
import com.example.homework.order.domain.PurchaseOrder;
import com.example.homework.payment.application.port.OrderPaymentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderPaymentAdapter implements OrderPaymentPort {

    private final OrderRepositoryPort orderRepository;

    @Override
    public void markOrderPaid(UUID orderId) {
        PurchaseOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));

        order.markPaid();              // 도메인 행위로 상태 변경
        orderRepository.save(order);   // 저장
    }
}
