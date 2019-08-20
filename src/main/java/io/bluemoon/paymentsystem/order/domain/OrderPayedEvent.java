package io.bluemoon.paymentsystem.order.domain;

import io.bluemoon.paymentsystem.generic.money.domain.Money;

public class OrderPayedEvent {
    private Order order;

    public OrderPayedEvent(Order order) {
        this.order = order;
    }

    public Long getOrderId() {
        return order.getId();
    }

    public Long getProductId() {
        return order.getProductId();
    }

    public Money getTotalPrice() {
        return order.caculateTotalPrice();
    }
}
