package io.bluemoon.paymentsystem.product.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table
@Getter
public class Product {
    public enum ProductStatus{ACTIVE, INAVTIVE}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private ProductStatus status;


    @Builder
    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
        this.status = ProductStatus.ACTIVE;

    }

    Product() {

    }


}
