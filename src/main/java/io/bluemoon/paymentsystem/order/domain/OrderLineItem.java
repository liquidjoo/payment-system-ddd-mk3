package io.bluemoon.paymentsystem.order.domain;

import io.bluemoon.paymentsystem.generic.money.domain.Money;
import io.bluemoon.paymentsystem.plan.domain.Option;
import io.bluemoon.paymentsystem.plan.domain.OptionGroup;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "order_line_item")
@Getter
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_item_id")
    private Long id;

    @Column
    private Long planId;

    @Column(name = "plan_name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_line_item_id")
    private List<OrderOptionGroup> groups = new ArrayList<>();

    public OrderLineItem(Long planId, String name, List<OrderOptionGroup> groups) {
        this(null, planId, name, groups);
    }

    @Builder
    public OrderLineItem(Long id, Long planId, String name, List<OrderOptionGroup> groups) {
        this.id = id;
        this.planId = planId;
        this.name = name;
        this.groups.addAll(groups);
    }

    OrderLineItem() {

    }

    public Money calculatePrice() {
        return Money.sum(groups, OrderOptionGroup::calcaulatePrice);
    }

    private List<OptionGroup> convertToOptionGroups() {
        return groups.stream().map(OrderOptionGroup::convertToOptionGroup).collect(Collectors.toList());
    }


    @Embeddable
    @Getter
    public static class OrderOption {
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
}
