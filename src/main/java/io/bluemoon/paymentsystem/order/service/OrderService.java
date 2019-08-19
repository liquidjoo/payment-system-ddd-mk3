package io.bluemoon.paymentsystem.order.service;

import io.bluemoon.paymentsystem.order.domain.Order;
import io.bluemoon.paymentsystem.order.domain.OrderRepository;
import io.bluemoon.paymentsystem.order.domain.OrderValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
