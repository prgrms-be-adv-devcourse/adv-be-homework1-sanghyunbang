package com.example.homework.payment.application.usecase;

import com.example.homework.payment.domain.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetPaymentsUseCase {
    Page<Payment> getPayments(Pageable pageable);
}
