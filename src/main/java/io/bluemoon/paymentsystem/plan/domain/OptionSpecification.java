package io.bluemoon.paymentsystem.plan.domain;

import io.bluemoon.paymentsystem.generic.money.domain.Money;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
@Getter
public class OptionSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_spec_id")
    private Long id;

    @Column
    private String name;

    @Column
    private Money price;

    @Builder
    public OptionSpecification(Long id, String name, Money price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    OptionSpecification() {
    }

    public boolean isSatisfiedBy(Option option) {
        return Objects.equals(name, option.getName()) && Objects.equals(price, option.getPrice());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof OptionSpecification)) {
            return false;
        }

        OptionSpecification other = (OptionSpecification) obj;
        return Objects.equals(name, other.getName()) && Objects.equals(price, other.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
