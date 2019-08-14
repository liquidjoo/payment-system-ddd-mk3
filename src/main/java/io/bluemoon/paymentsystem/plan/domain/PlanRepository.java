package io.bluemoon.paymentsystem.plan.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findByProductId(Long productId);
}
