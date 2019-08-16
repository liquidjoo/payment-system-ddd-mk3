package io.bluemoon.paymentsystem.order.domain;

import io.bluemoon.paymentsystem.generic.money.domain.Money;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table
@Getter
public class Order extends AbstractAggregateRoot<Order> {
    public enum OrderStatus {ORDERED, PAYED, COMPLETED}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "product_id")
    private Long productId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    @Column(name = "ordered_time")
    private LocalDateTime orderedTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus orderStatus;

    public Order(String projectId, Long productId, List<OrderLineItem> items) {
        this(null, projectId, productId, items, LocalDateTime.now(), null);
    }

    @Builder
    public Order (Long id, String projectId, Long productId, List<OrderLineItem> items, LocalDateTime orderedTime, OrderStatus status) {
        this.id = id;
        this.projectId = projectId;
        this.productId = productId;
        this.orderedTime = orderedTime;
        this.orderStatus = status;
        this.orderLineItems.addAll(items);
    }

    Order() {

    }

    public List<Long> getPlanIds() {
        return orderLineItems.stream().map(OrderLineItem::getPlanId).collect(Collectors.toList());
    }

    private void ordered() {
        this.orderStatus = OrderStatus.ORDERED;
    }

    public Money caculateTotalPrice() {
        return Money.sum(orderLineItems, OrderLineItem::calculatePrice);
    }

}
