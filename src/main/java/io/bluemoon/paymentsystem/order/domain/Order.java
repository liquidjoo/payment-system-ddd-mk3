package io.bluemoon.paymentsystem.order.domain;

import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;

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




}
