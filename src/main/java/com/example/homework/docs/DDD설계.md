## 도메인 모델

### 주문 (PurchaseOrder)
_Entity_

#### 정의
- 구매자가 특정 상품을 특정 금액으로 구매하기 위해서 생성하는 "주문" 도메인 객체

- 주문은 **고유 식별자(id)** 를 가지며, 생성 이후 상태 전이를 통해 생명주기를 가진다

#### 속성

- id(UUID) : 주문번호 - ID (주문의 고유 식별자 -> 절대 변경 안됨)
- productId(UUID) : 제품번호 (주문 대상 상품의 식별자 -> 주문 생성 이후 변경 안됨)
- sellerId(UUID) : 판매자번호 (주문 생성 이후 변경 불가)
- memberId(UUID) : 구매자번호 (주문 생성 이후 변경 불가)
- amount(BigDecimal) : 주문금액 (주문 수량이 빠져있어서 조금 이상..)
- status(PurcahseOrderStatus) : 주문의 현재 상태 
- createdAt / updatedAt(LocalDateTime) : 주문 생성 / 수정 시각

#### 행위

- create(...) 
  - 주문 생성 규칙에 따라 새 주문을 만든다
  - 생성 시 status는 CREATED로 시작한다

- markPaid()
  - 결제 완료 처리. CREATED 상태에서만 가능

- cancel()
  - 주문 취소 처리. PAID 상태에서는 불가능

#### 규칙 / 불변식 (invariant)

1. id는 생성 이후 불면
2. product/sellId/memberId는 null 불가
3. amount는 0보다 커야 한다
4. 상태 전이 규칙
   - CREATED -> PAID 가능
   - CREATED -> CANCELLED 가능
   - PAID -> CANCELLED 불가
5. 결제 완료 이후에는 재결제/재생성 불가능
6. updatedAt은 createdAt보다 이전일 수 없다

### 주문 상태(PurchaseOrderStatus)

_ENUM_

#### 정의
주문이 생성주기 동안 가질 수 있는 상태의 집합
- CREATED : 주문 생성됨(미결제)
- PAID : 결제 완료됨
- CANCELLED : 취소됨