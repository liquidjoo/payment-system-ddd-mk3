package io.bluemoon.paymentsystem.order.domain;

import io.bluemoon.paymentsystem.generic.money.domain.Money;
import io.bluemoon.paymentsystem.plan.domain.OptionGroup;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table
@Getter
public class OrderOptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_option_group_id")
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "order_option", joinColumns = @JoinColumn(name = "order_option_group_id"))
    private List<OrderOption> orderOptions;

    public OrderOptionGroup(String name, List<OrderOption> options) {
        this(null, name, options);
    }

    @Builder
    public OrderOptionGroup(Long id, String name, List<OrderOption> options) {
        this.id = id;
        this.name = name;
        this.orderOptions = options;
    }

    OrderOptionGroup() {

    }

    public Money calcaulatePrice() {
        return Money.sum(orderOptions, OrderOption::getPrice);
    }

    public OptionGroup convertToOptionGroup() {
        return new OptionGroup(name, orderOptions.stream().map(OrderOption::convertToOption).collect(Collectors.toList()));
    }

}
