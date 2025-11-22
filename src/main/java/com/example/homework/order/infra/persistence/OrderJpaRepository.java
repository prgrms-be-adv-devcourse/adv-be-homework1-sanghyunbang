package com.example.homework.order.infra.persistence;

import com.example.homework.order.domain.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderJpaRepository extends JpaRepository<PurchaseOrder, UUID> {

}
