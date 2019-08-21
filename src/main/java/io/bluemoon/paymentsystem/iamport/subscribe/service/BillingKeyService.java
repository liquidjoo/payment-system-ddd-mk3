package io.bluemoon.paymentsystem.iamport.subscribe.service;

import io.bluemoon.paymentsystem.iamport.subscribe.domain.BillingKey;
import io.bluemoon.paymentsystem.iamport.subscribe.domain.BillingKeyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BillingKeyService {

    private BillingKeyRepository billingKeyRepository;

    public BillingKeyService(BillingKeyRepository billingKeyRepository) {
        this.billingKeyRepository = billingKeyRepository;
    }

    @Transactional
    public void preparingBillingKey(String customerId) {
        BillingKey billingKey = BillingKey.builder()
                .build();

        billingKey.preparing();
        billingKeyRepository.save(billingKey);
    }
}