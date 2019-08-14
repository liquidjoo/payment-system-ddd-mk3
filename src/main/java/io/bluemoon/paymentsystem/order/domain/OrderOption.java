package io.bluemoon.paymentsystem.order.domain;

import io.bluemoon.paymentsystem.generic.money.domain.Money;
import io.bluemoon.paymentsystem.plan.domain.Option;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class OrderOption {
    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Money price;

    @Builder
    public OrderOption(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    OrderOption() {

    }

    public Option convertToOption() {
        return new Option(name, price);
    }
}
