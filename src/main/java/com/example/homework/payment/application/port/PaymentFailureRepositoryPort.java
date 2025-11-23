package com.example.homework.payment.application.port;

import com.example.homework.payment.domain.PaymentFailure;

public interface PaymentFailureRepositoryPort {
    PaymentFailure save(PaymentFailure failure);
}
