package io.bluemoon.paymentsystem.iamport.subscribe.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "billing_key")
@Getter
public class BillingKey extends AbstractAggregateRoot<BillingKey> {

    public enum BillingKeyStatus {ACTIVE, INACTIVE, PREPARING}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billing_key_id")
    private Long id;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "customer_uid")
    private String customerUid;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BillingKeyStatus billingKeyStatus;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    BillingKey() {
    }

    @Builder
    public BillingKey(Long id, String projectId, String customerUid, BillingKeyStatus billingKeyStatus, LocalDateTime regDate) {
        this.id = id;
        this.projectId = projectId;
        this.customerUid = customerUid;
        this.billingKeyStatus = billingKeyStatus;
        this.regDate = regDate;
    }

    public void preparing() {
        this.billingKeyStatus = BillingKeyStatus.PREPARING;
    }


}
