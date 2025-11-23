// payment/presentation/mapper/PaymentResponseMapper.java
package com.example.homework.payment.presentation.mapper;

import com.example.homework.payment.domain.Payment;
import com.example.homework.payment.domain.PaymentFailure;
import com.example.homework.payment.presentation.dto.PaymentFailureResponse;
import com.example.homework.payment.presentation.dto.PaymentResponse;

public class PaymentResponseMapper {

    private PaymentResponseMapper() {}

    public static PaymentResponse toResponse(Payment p) {
        return new PaymentResponse(
                p.getId(),
                p.getOrderId(),
                p.getPaymentKey(),
                p.getAmount(),
                p.getStatus(),
                p.getMethod(),
                p.getRequestedAt(),
                p.getApprovedAt(),
                p.getFailReason()
        );
    }

    public static PaymentFailureResponse toFailureResponse(PaymentFailure f) {
        return new PaymentFailureResponse(
                f.getId(),
                f.getOrderId(),
                f.getPaymentKey(),
                f.getErrorCode(),
                f.getErrorMessage(),
                f.getAmount(),
                f.getCreatedAt()
        );
    }
}
