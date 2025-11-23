package com.example.homework.order.application.port;

import com.example.homework.order.domain.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

/**
 * Order 에 대한 Core의 요구사항들
 * Core는 Jpa, DB 같은 기술들을 몰라야 하기 때문에 이 포트 인터페이스를 만들어
 * 여기에만 의존하게 한다.
 * */
public interface OrderRepositoryPort {

    PurchaseOrder save(PurchaseOrder order);

    Optional<PurchaseOrder> findById(UUID id);

    Page<PurchaseOrder> findAll(Pageable pageable);
}
