package com.example.homework.order.infra.persistence;

import com.example.homework.order.application.port.OrderRepositoryPort;
import com.example.homework.order.domain.PurchaseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JpaOrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository jpaRepository;

    @Override
    public PurchaseOrder save(PurchaseOrder order) {
        PurchaseOrderJpaEntity saved =
                jpaRepository.save(PurchaseOrderMapper.toJpaEntity(order));
        return PurchaseOrderMapper.toDomain(saved);
    }

    @Override
    public Optional<PurchaseOrder> findById(UUID id) {
        return jpaRepository.findById(id).map(PurchaseOrderMapper::toDomain);
    }

    @Override
    public List<PurchaseOrder> findAll(int page, int size) {
        return jpaRepository.findAll(PageRequest.of(page, size))
                .map(PurchaseOrderMapper::toDomain)
                .getContent();
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

}
