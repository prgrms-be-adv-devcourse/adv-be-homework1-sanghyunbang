package com.example.homework.order.application.usecase;

import com.example.homework.order.application.usecase.command.ChangeOrderStatusCommand;
import com.example.homework.order.domain.PurchaseOrder;

public interface ChangeOrderStatusUseCase {
    PurchaseOrder changeStatus(ChangeOrderStatusCommand command);
}
