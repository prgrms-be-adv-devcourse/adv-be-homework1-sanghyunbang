package com.example.homework.order.infra.persistence;

import com.example.homework.order.application.port.OrderRepositoryPort;
import com.example.homework.order.domain.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class JpaOrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository jpaRepository;

    public JpaOrderRepositoryAdapter(OrderJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public PurchaseOrder save(PurchaseOrder order){
        PurchaseOrderJpaEntity saved = jpaRepository.save(PurchaseOrderMapper.toJpaEntity(order));
        return PurchaseOrderMapper.toDomain(saved);
    }

    @Override
    public Optional<PurchaseOrder> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(PurchaseOrderMapper::toDomain);
    }

    @Override
    public Page<PurchaseOrder> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable).map(PurchaseOrderMapper::toDomain);
    }

}
