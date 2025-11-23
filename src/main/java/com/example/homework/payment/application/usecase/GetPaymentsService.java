package com.example.homework.payment.application.usecase;

import com.example.homework.payment.application.port.PaymentRepositoryPort;
import com.example.homework.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPaymentsService implements GetPaymentsUseCase {

    private final PaymentRepositoryPort paymentRepository;

    @Override
    public Page<Payment> getPayments(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }
}
