package com.example.homework.payment.infra.persistence;

import com.example.homework.payment.application.port.PaymentFailureRepositoryPort;
import com.example.homework.payment.domain.PaymentFailure;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JpaPaymentFailureRepositoryAdapter implements PaymentFailureRepositoryPort {

    private final PaymentFailureJpaRepository jpaRepository;

    @Override
    public PaymentFailure save(PaymentFailure failure) {
        PaymentFailureJpaEntity saved = jpaRepository.save(PaymentFailureMapper.toJpa(failure));
        return PaymentFailureMapper.toDomain(saved);
    }
}
