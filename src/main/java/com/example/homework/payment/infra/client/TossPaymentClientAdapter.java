package com.example.homework.payment.infra.client;

import com.example.homework.payment.application.port.PaymentGatewayPort;
import com.example.homework.payment.application.usecase.command.ConfirmPaymentCommand;
import com.example.homework.payment.infra.client.dto.TossConfirmResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TossPaymentClientAdapter implements PaymentGatewayPort {

    private static final String CONFIRM_URL = "https://api.tosspayments.com/v1/payments/confirm";

    private final RestTemplate restTemplate;
    private final TossPaymentProperties properties;

    @Override
    public TossConfirmResponse confirm(ConfirmPaymentCommand command) {

        if (properties.getSecretKey() == null || properties.getSecretKey().isBlank()) {
            throw new IllegalStateException("Toss secret key is not configured");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String auth = properties.getSecretKey() + ":";
        String encoded = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        headers.set(HttpHeaders.AUTHORIZATION, "Basic " + encoded);

        Map<String, Object> body = new HashMap<>();
        body.put("paymentKey", command.paymentKey());
        body.put("orderId", command.orderId());
        body.put("amount", command.amount());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            return restTemplate.postForObject(CONFIRM_URL, entity, TossConfirmResponse.class);
        } catch (HttpStatusCodeException ex) {
            throw new IllegalStateException(
                    "Toss confirm failed (" + ex.getStatusCode() + "): " + ex.getResponseBodyAsString(),
                    ex
            );
        }
    }
}
