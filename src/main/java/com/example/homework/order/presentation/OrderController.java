package com.example.homework.order.presentation;


import com.example.homework.common.ApiResponse;
import com.example.homework.order.application.usecase.ChangeOrderStatusUseCase;
import com.example.homework.order.application.usecase.CreateOrderUseCase;
import com.example.homework.order.application.usecase.GetOrdersUseCase;
import com.example.homework.order.application.usecase.command.CreateOrderCommand;
import com.example.homework.order.domain.PurchaseOrder;
import com.example.homework.order.domain.PurchaseOrderStatus;
import com.example.homework.order.presentation.dto.OrderCreateRequest;
import com.example.homework.order.presentation.dto.OrderResponse;
import com.example.homework.order.presentation.mapper.OrderCommandMapper;
import com.example.homework.order.presentation.mapper.OrderResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Orders", description = "주문 관련 API")
@RestController
@RequestMapping("${api.v1}/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrdersUseCase getOrdersUseCase;
    private final ChangeOrderStatusUseCase changeOrderStatusUseCase;
    private final OrderCommandMapper commandMapper;


    @Operation(summary = "주문 생성", description = "상품/판매자/구매자/액수 정보를 받아 주문을 생성합니다.")
    @PostMapping
    public ApiResponse<OrderResponse> create(@RequestBody OrderCreateRequest request){

        PurchaseOrder saved = createOrderUseCase.create(
                commandMapper.toCreateCommand(request)
        );

        return ApiResponse.created(OrderResponseMapper.toResponse(saved));
    }

    @Operation(summary = "주문 목록 조회")
    @GetMapping
    public ApiResponse<List<OrderResponse>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        List<PurchaseOrder> orders =
                getOrdersUseCase.getOrders(commandMapper.toGetOrdersQuery(page, size));

        long total = getOrdersUseCase.getTotalCount();

        List<OrderResponse> data = orders.stream()
                .map(OrderResponseMapper::toResponse)
                .toList();

        return ApiResponse.ok(data, total);
    }

    @Operation(summary = "결제 처리")
    @PatchMapping("{id}/paid")
    public ApiResponse<OrderResponse> paid(@PathVariable String id) {
        PurchaseOrder saved = changeOrderStatusUseCase.changeStatus(
                commandMapper.toChangeStatusCommand(id, PurchaseOrderStatus.PAID)
        );
        return ApiResponse.ok(OrderResponseMapper.toResponse(saved));
    }

    @Operation(summary = "결제 취소")
    @PatchMapping("{id}/canceled")
    public ApiResponse<OrderResponse> canceled(@PathVariable String id) {
        PurchaseOrder saved = changeOrderStatusUseCase.changeStatus(
                commandMapper.toChangeStatusCommand(id, PurchaseOrderStatus.CANCELLED)
        );
        return ApiResponse.ok(OrderResponseMapper.toResponse(saved));
    }

}
