package com.example.homework.order.application;



import com.example.homework.common.ResponseEntity;
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
    @Autowired
    private OrderJpaRepository jpaRepository;
    public ResponseEntity<PurchaseOrder> create(PurchaseOrder request) {
        request.setSellerId(UUID.randomUUID());
        request.setMemberId(UUID.randomUUID());
        request.setProductId(UUID.randomUUID());
        return new ResponseEntity<>(HttpStatus.CREATED.value(), jpaRepository.save(request),1);

    }

    public ResponseEntity<List<PurchaseOrder>> findAll(Pageable pageable) {
        Page<PurchaseOrder> purchaseOrderPage = jpaRepository.findAll(pageable);
        return new ResponseEntity<>(HttpStatus.OK.value(), purchaseOrderPage.stream().toList(), purchaseOrderPage.getTotalElements());

    }

    public ResponseEntity<PurchaseOrder> statusChange(String id, PurchaseOrderStatus status) {
        Optional<PurchaseOrder> order = jpaRepository.findById(UUID.fromString(id)); // Optional 로 리턴
        if(order.isPresent()) {
            PurchaseOrder item = order.get();
            item.setStatus(status);
            return new ResponseEntity<>(HttpStatus.CREATED.value(), jpaRepository.save(item), 1);
        }else{
            throw new IllegalArgumentException("Order not found: "+id);
        }
    }
}
