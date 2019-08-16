package io.bluemoon.paymentsystem.order.service;

import io.bluemoon.paymentsystem.generic.money.domain.Money;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class Cart {

    private Long productId;
    private String projectId;
    private List<CartLineItem> cartLineItems = new ArrayList<>();

    public Cart(Long productId, String projectId, CartLineItem ... cartLineItems) {
        this.productId = productId;
        this.projectId = projectId;
        this.cartLineItems = Arrays.asList(cartLineItems);
    }


    @Data
    @NoArgsConstructor
    public static class CartLineItem {
        private Long planId;
        private String name;
        private List<CartOptionGroup> groups = new ArrayList<>();

        public CartLineItem(Long planId, String name, CartOptionGroup ... groups) {
            this.planId = planId;
            this.name = name;
            this.groups = Arrays.asList(groups);
        }

    }

    @Data
    @NoArgsConstructor
    public static class CartOptionGroup {
        private String name;
        private List<CartOption> options = new ArrayList<>();

        public CartOptionGroup(String name, CartOption ... options) {
            this.name = name;
            this.options = Arrays.asList(options);
        }
    }

    @Data
    @NoArgsConstructor
    public static class CartOption {
        private String name;
        private Money price;

        public CartOption(String name, Money price) {
            this.name = name;
            this.price = price;
        }
    }
}
