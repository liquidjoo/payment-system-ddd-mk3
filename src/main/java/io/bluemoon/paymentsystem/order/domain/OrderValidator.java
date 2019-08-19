package io.bluemoon.paymentsystem.order.domain;

import io.bluemoon.paymentsystem.plan.domain.OptionGroupSpecification;
import io.bluemoon.paymentsystem.plan.domain.Plan;
import io.bluemoon.paymentsystem.plan.domain.PlanRepository;
import io.bluemoon.paymentsystem.product.domain.Product;
import io.bluemoon.paymentsystem.product.domain.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Component
public class OrderValidator {
    private ProductRepository productRepository;
    private PlanRepository planRepository;

    public OrderValidator(ProductRepository productRepository,
                          PlanRepository planRepository) {
        this.productRepository = productRepository;
        this.planRepository = planRepository;
    }

    public void validate(Order order) {
        validate(order, getProduct(order), getPlans(order));
    }


    void validate(Order order, Product product, Map<Long, Plan> plans) {
        if (!product.getStatus().toString().equals("ACTIVE")) {
            System.out.println(product.getStatus().toString());
            System.out.println(product.getId());
            System.out.println(product.getName());
            throw new IllegalArgumentException("닫힌 상품");
        }

        if (order.getOrderLineItems().isEmpty()) {
            throw new IllegalArgumentException("주문 항목이 비어 있습니다.");
        }

        for (OrderLineItem item : order.getOrderLineItems()) {
            validateOrderLineItem(item, plans.get(item.getPlanId()));
        }
    }

    private void validateOrderLineItem(OrderLineItem item, Plan plan) {
        if (!plan.getName().equals(item.getName())) {
            throw new IllegalArgumentException("기본 상품이 변경됐습니다.");
        }

        for (OrderOptionGroup group : item.getGroups()) {
            validateOrderOptionGroup(group, plan);
        }
    }

    private void validateOrderOptionGroup(OrderOptionGroup group, Plan plan) {
        for (OptionGroupSpecification spec : plan.getOptionGroupSpecs()) {
            if (spec.isSatisfiedBy(group.convertToOptionGroup())) {
                return;
            }
        }

        throw new IllegalArgumentException("메뉴가 변경됐습니다.");
    }

    private Product getProduct(Order order) {
        return productRepository.findById(order.getProductId()).orElseThrow(IllegalArgumentException::new);
    }

    private Map<Long, Plan> getPlans(Order order) {
        return planRepository.findAllById(order.getPlanIds()).stream().collect(toMap(Plan::getId, identity()));
    }
}
