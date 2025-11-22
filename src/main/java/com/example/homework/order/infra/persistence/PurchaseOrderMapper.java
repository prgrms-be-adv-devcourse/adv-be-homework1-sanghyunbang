package com.example.homework.order.infra.persistence;

import com.example.homework.order.domain.PurchaseOrder;

public class PurchaseOrderMapper {

    private PurchaseOrderMapper() {}

    public static PurchaseOrderJpaEntity toJpaEntity(PurchaseOrder d) {

        return PurchaseOrderJpaEntity.builder()
                .id(d.getId())
                .productId(d.getProductId())
                .sellerId(d.getSellerId())
                .memberId(d.getMemberId())
                .amount(d.getAmount())
                .status(d.getStatus())
                .createdAt(d.getCreatedAt())
                .updatedAt(d.getUpdatedAt())
                .build();
    }

    public static PurchaseOrder toDomain(PurchaseOrderJpaEntity e) {
        return PurchaseOrder.restore(
                e.getId(),
                e.getProductId(),
                e.getSellerId(),
                e.getMemberId(),
                e.getAmount(),
                e.getStatus(),
                e.getCreatedAt(),
                e.getUpdatedAt()
        );
    }
}
