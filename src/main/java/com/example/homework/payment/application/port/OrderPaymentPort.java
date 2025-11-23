package com.example.homework.payment.application.port;

import java.util.UUID;

public interface OrderPaymentPort {
    void markOrderPaid(UUID orderId);
}
