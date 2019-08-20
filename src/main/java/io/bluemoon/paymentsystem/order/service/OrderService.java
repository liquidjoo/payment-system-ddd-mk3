package io.bluemoon.paymentsystem.order.service;

import io.bluemoon.paymentsystem.order.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderValidator orderValidator;
    private OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository,
                        OrderValidator orderValidator,
                        OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public void placeOrder(Cart cart) {
        Order order = orderMapper.mapFrom(cart);
        order.place(orderValidator);

        System.out.println(order);
        orderRepository.save(order);
    }
    public Order getOrder() {
        Order order = orderRepository.findById(2L).orElseThrow(IllegalArgumentException::new);
        return Order.builder()
                .id(order.getId())
                .productId(order.getProductId())
                .projectId(order.getProjectId())
                .items(order.getOrderLineItems().stream().map(this::toOrderLineItem).collect(Collectors.toList()))
                .status(order.getOrderStatus())
                .orderedTime(order.getOrderedTime())
                .build();
    }

    private OrderLineItem toOrderLineItem(OrderLineItem orderLineItem) {
        return OrderLineItem.builder()
                .id(orderLineItem.getId())
                .planId(orderLineItem.getPlanId())
                .name(orderLineItem.getName())
                .groups(orderLineItem.getGroups().stream().map(this::toOrderOptionGroup).collect(Collectors.toList()))
                .build();
    }

    private OrderOptionGroup toOrderOptionGroup(OrderOptionGroup orderOptionGroup) {
        return OrderOptionGroup.builder()
                .id(orderOptionGroup.getId())
                .name(orderOptionGroup.getName())
                .options(orderOptionGroup.getOrderOptions().stream().map(this::toOrderOption).collect(Collectors.toList()))
                .build();
    }

    private OrderLineItem.OrderOption toOrderOption(OrderLineItem.OrderOption orderOption) {
        return OrderLineItem.OrderOption.builder()
                .name(orderOption.getName())
                .price(orderOption.getPrice())
                .build();
    }
}
