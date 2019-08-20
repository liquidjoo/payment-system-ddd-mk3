package io.bluemoon.paymentsystem.order.service;

import io.bluemoon.paymentsystem.order.domain.Order;
import io.bluemoon.paymentsystem.order.domain.OrderLineItem;
import io.bluemoon.paymentsystem.order.domain.OrderOptionGroup;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public Order mapFrom(Cart cart) {
        return new Order(
                cart.getProjectId(),
                cart.getProductId(),
                cart.getCartLineItems().stream().map(this::toOrderLineItem).collect(Collectors.toList()));
    }


    private OrderLineItem toOrderLineItem(Cart.CartLineItem cartLineItem) {
        return new OrderLineItem(
                cartLineItem.getPlanId(),
                cartLineItem.getName(),
                cartLineItem.getGroups().stream().map(this::toOrderOptionGroup).collect(Collectors.toList()));
    }


    private OrderOptionGroup toOrderOptionGroup(Cart.CartOptionGroup cartOptionGroup) {
        return new OrderOptionGroup(
                cartOptionGroup.getName(),
                cartOptionGroup.getOptions().stream().map(this::toOrderOption).collect(Collectors.toList())
        );
    }

    private OrderLineItem.OrderOption toOrderOption(Cart.CartOption cartOption) {
        return new OrderLineItem.OrderOption(
                cartOption.getName(),
                cartOption.getPrice());
    }
}
