package io.bluemoon.paymentsystem.iamport.subscribe.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingKeyRepository extends JpaRepository<BillingKey, Long> {
}
