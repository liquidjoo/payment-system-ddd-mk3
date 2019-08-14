package io.bluemoon.paymentsystem.plan.domain;

import io.bluemoon.paymentsystem.generic.money.domain.Money;
import lombok.Data;

@Data
public class PlanBoard {
    private Long productId;
    private String productName;
    private boolean status;



    @Data
    public static class PlanItem{
        private Long planId;
        private String planName;
        private Money planBasePrice;
        private String planDescription;

        public PlanItem(Plan plan) {
            this.planId = plan.getId();
            this.planName = plan.getName();
            this.planBasePrice = plan.getBasePrice();
            this.planDescription = plan.getDescription();
        }
    }
}
