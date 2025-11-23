package com.example.homework.order.application;



import com.example.homework.common.ResponseEntity;
import com.example.homework.order.application.port.OrderRepositoryPort;
import com.example.homework.order.infra.persistence.OrderJpaRepository;
import com.example.homework.order.domain.PurchaseOrder;
import com.example.homework.order.domain.PurchaseOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    private final OrderRepositoryPort orderRepository;

    // [문제상황] OrderService가 OrderJpaRepository(Infra)에 직접 의존
    // port만 주입받는다 (Adapter가 자동으로 연결됨)
    public OrderService(OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<PurchaseOrder> create(PurchaseOrder request) {

        // 기존에 있던 Setter는 사용 지양 DDD에서
        // request 값으로 도메인 규칙에 맞는 새 객체를 생성
        // [임시처리] DTO 쓰지 않고 임시 처리 상황
        PurchaseOrder order = PurchaseOrder.create(
                request.getProductId(),
                request.getSellerId(),
                request.getMemberId(),
                request.getAmount()
        );

        PurchaseOrder saved =  orderRepository.save(order);

        return new ResponseEntity<>(HttpStatus.CREATED.value(), orderRepository.save(request),1);

    }

    public ResponseEntity<List<PurchaseOrder>> findAll(Pageable pageable) {
        Page<PurchaseOrder> purchaseOrderPage = orderRepository.findAll(pageable);
        return new ResponseEntity<>(HttpStatus.OK.value(), purchaseOrderPage.stream().toList(), purchaseOrderPage.getTotalElements());

    }

    public ResponseEntity<PurchaseOrder> statusChange(String id, PurchaseOrderStatus status) {

        PurchaseOrder order = orderRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException("Order ID " + id + " not found"));

        // setter로 직접 바꾸지 않고 도메인 행위로 변경하기
        if(status == PurchaseOrderStatus.PAID) {
            order.markPaid();
        }else if(status == PurchaseOrderStatus.PAID){
            order.cancel();
        }else {
            throw new IllegalArgumentException("Order not found: "+id);
        }

        PurchaseOrder saved = orderRepository.save(order);

        return new ResponseEntity<>(HttpStatus.OK.value(), saved,1);
    }
}
