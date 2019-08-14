package io.bluemoon.paymentsystem.plan.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table
@Getter
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;

    @Column(name = "plan_name")
    private String name;

    @Column(name = "plan_description")
    private String description;

    @Column(name = "product_id")
    private Long productId;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "plan_id")
//    private


}
