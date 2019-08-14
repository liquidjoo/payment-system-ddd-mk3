package io.bluemoon.paymentsystem.plan.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table
@Getter
public class OptionGroupSpecificaion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "exclusive")
    private boolean exclusive;

    @Column(name = "basic")
    private boolean basic;
}
