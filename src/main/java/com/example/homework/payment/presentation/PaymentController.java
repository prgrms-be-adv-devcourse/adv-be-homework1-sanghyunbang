// payment/presentation/PaymentController.java
package com.example.homework.payment.presentation;

import com.example.homework.common.ApiResponse;
import com.example.homework.payment.application.usecase.ConfirmPaymentUseCase;
import com.example.homework.payment.application.usecase.GetPaymentsUseCase;
import com.example.homework.payment.application.usecase.RecordPaymentFailureUseCase;
import com.example.homework.payment.domain.Payment;
import com.example.homework.payment.domain.PaymentFailure;
import com.example.homework.payment.presentation.dto.PaymentConfirmRequest;
import com.example.homework.payment.presentation.dto.PaymentFailRequest;
import com.example.homework.payment.presentation.dto.PaymentFailureResponse;
import com.example.homework.payment.presentation.dto.PaymentResponse;
import com.example.homework.payment.presentation.mapper.PaymentCommandMapper;
import com.example.homework.payment.presentation.mapper.PaymentResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.v1}/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final GetPaymentsUseCase getPaymentsUseCase;
    private final ConfirmPaymentUseCase confirmPaymentUseCase;
    private final RecordPaymentFailureUseCase recordPaymentFailureUseCase;
    private final PaymentCommandMapper commandMapper;

    @Operation(summary = "결제 내역 조회", description = "확정/실패된 결제 정보를 페이지 단위로 조회한다.")
    @GetMapping
    public ApiResponse<List<PaymentResponse>> findAll(Pageable pageable) {

        Page<Payment> page = getPaymentsUseCase.getPayments(pageable);

        List<PaymentResponse> data = page.getContent().stream()
                .map(PaymentResponseMapper::toResponse)
                .toList();

        return ApiResponse.ok(data, page.getTotalElements());
    }

    @Operation(summary = "토스 결제 승인", description = "토스 결제 완료 후 paymentKey/orderId/amount를 전달받아 결제를 승인한다.")
    @PostMapping("/confirm")
    public ApiResponse<PaymentResponse> confirm(@RequestBody PaymentConfirmRequest request) {

        Payment saved = confirmPaymentUseCase.confirm(
                commandMapper.toConfirmCommand(request)
        );

        return ApiResponse.created(PaymentResponseMapper.toResponse(saved));
    }

    @Operation(summary = "결제 실패 기록", description = "토스 결제 실패 정보를 저장한다.")
    @PostMapping("/fail")
    public ApiResponse<PaymentFailureResponse> fail(@RequestBody PaymentFailRequest request) {

        PaymentFailure saved = recordPaymentFailureUseCase.recordFail(
                commandMapper.toFailCommand(request)
        );

        return ApiResponse.ok(PaymentResponseMapper.toFailureResponse(saved));
    }
}
