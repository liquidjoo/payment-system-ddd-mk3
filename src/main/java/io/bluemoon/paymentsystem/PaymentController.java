package io.bluemoon.paymentsystem;

import io.bluemoon.paymentsystem.generic.money.domain.Money;
import io.bluemoon.paymentsystem.order.service.Cart;
import io.bluemoon.paymentsystem.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private OrderService orderService;

    public PaymentController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping(value = "/order")
    public void test() {
        Cart cart = new Cart(1L, "abcdef",
                        new Cart.CartLineItem(1L, "아라비카",
                                new Cart.CartOptionGroup("기본",
                                        new Cart.CartOption("100g", Money.wons(7000)))));

        orderService.placeOrder(cart);
    }
}
