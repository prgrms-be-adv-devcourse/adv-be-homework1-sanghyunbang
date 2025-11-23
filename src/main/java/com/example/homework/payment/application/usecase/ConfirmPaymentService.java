package com.example.homework.payment.application.usecase;

import com.example.homework.payment.application.port.OrderPaymentPort;
import com.example.homework.payment.application.port.PaymentGatewayPort;
import com.example.homework.payment.application.port.PaymentRepositoryPort;
import com.example.homework.payment.application.usecase.command.ConfirmPaymentCommand;
import com.example.homework.payment.domain.Payment;
import com.example.homework.payment.infra.client.dto.TossConfirmResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmPaymentService implements ConfirmPaymentUseCase {

    private final PaymentGatewayPort gatewayPort;
    private final PaymentRepositoryPort paymentRepository;
    private final OrderPaymentPort orderPaymentPort;

    @Override
    public Payment confirm(ConfirmPaymentCommand command) {

        TossConfirmResponse toss = gatewayPort.confirm(command);

        Payment payment = Payment.ready(
                toss.paymentKey(),
                toss.orderId(),
                toss.totalAmount()
        );

        LocalDateTime approvedAt = toss.approvedAtLocal();
        LocalDateTime requestedAt = toss.requestedAtLocal();

        payment.confirm(toss.method(), approvedAt, requestedAt);

        Payment saved = paymentRepository.save(payment);

        // 주문 상태 변경 (payment core는 order 내부를 모름)
        orderPaymentPort.markOrderPaid(UUID.fromString(toss.orderId()));

        return saved;
    }
}
