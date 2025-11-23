package com.example.homework.order.application.usecase;

import com.example.homework.order.application.port.OrderRepositoryPort;
import com.example.homework.order.application.usecase.command.CreateOrderCommand;
import com.example.homework.order.domain.PurchaseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

public interface CreateOrderUseCase {
    PurchaseOrder create(CreateOrderCommand command);
}
