package com.example.homework.payment.infra.persistence;

import com.example.homework.payment.application.port.PaymentRepositoryPort;
import com.example.homework.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JpaPaymentRepositoryAdapter implements PaymentRepositoryPort {

    private final PaymentJpaRepository jpaRepository;

    @Override
    public Payment save(Payment payment) {
        PaymentJpaEntity saved = jpaRepository.save(PaymentMapper.toJpa(payment));
        return PaymentMapper.toDomain(saved);
    }

    @Override
    public Optional<Payment> findById(UUID id) {
        return jpaRepository.findById(id).map(PaymentMapper::toDomain);
    }

    @Override
    public Page<Payment> findAll(Pageable pageable) {
        return jpaRepository.findAll(pageable).map(PaymentMapper::toDomain);
    }
}
