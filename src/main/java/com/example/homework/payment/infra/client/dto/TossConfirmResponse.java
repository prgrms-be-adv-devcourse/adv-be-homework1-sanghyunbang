// payment/infra/client/dto/TossConfirmResponse.java
package com.example.homework.payment.infra.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TossConfirmResponse(
        String paymentKey,
        String orderId,
        @JsonProperty("totalAmount") Long totalAmount,
        String method,
        String status,
        OffsetDateTime requestedAt,
        OffsetDateTime approvedAt
) {
    public LocalDateTime requestedAtLocal() {
        return requestedAt == null ? null : requestedAt.toLocalDateTime();
    }

    public LocalDateTime approvedAtLocal() {
        return approvedAt == null ? null : approvedAt.toLocalDateTime();
    }
}
