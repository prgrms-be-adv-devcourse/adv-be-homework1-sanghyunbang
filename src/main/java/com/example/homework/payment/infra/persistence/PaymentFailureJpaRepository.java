package com.example.homework.payment.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentFailureJpaRepository extends JpaRepository<PaymentFailureJpaEntity, UUID> {}
