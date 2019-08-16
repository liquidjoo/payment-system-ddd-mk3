package io.bluemoon.paymentsystem.order.domain;

import io.bluemoon.paymentsystem.plan.domain.PlanRepository;
import io.bluemoon.paymentsystem.product.domain.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {
    private ProductRepository productRepository;
    private PlanRepository planRepository;
}
