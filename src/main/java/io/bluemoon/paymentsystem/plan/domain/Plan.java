package io.bluemoon.paymentsystem.plan.domain;

import io.bluemoon.paymentsystem.generic.money.domain.Money;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_id")
    private List<OptionGroupSpecification> optionGroupSpecs = new ArrayList<>();

    @Builder
    public Plan(Long id, Long productId, String name, String description, OptionGroupSpecification basic, List<OptionGroupSpecification> additives) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.optionGroupSpecs.add(basic);
        this.optionGroupSpecs.addAll(additives);
    }

    public Plan(Long productId, String name, String description, OptionGroupSpecification basic, OptionGroupSpecification... groups) {
        this(null, productId, name, description, basic, Arrays.asList(groups));
    }

    Plan() {

    }

    public Money getBasePrice() {
        return getBasicOptionGroupSpecs().getOptionSpecs().get(0).getPrice();
    }

    private OptionGroupSpecification getBasicOptionGroupSpecs() {
        return optionGroupSpecs
                .stream()
                .filter(spec -> spec.isBasic())
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

//    public void validateOrder(String planName, List<OptionGroup> optionGroups) {
//        if (!this.name.equals(planName)) {
//            throw new IllegalArgumentException("기본 상품이 변경됐습니다.");
//        }
//
//        if (!isSatisfiedBy(optionGroups)) {
//            throw new IllegalArgumentException("메뉴가 변경됐습니다.");
//        }
//    }
//
//    private boolean isSatisfiedBy(List<OptionGroup> cartOptionGroups) {
//        return cartOptionGroups.stream().anyMatch(this::isSatisfiedBy);
//    }
//
//    private boolean isSatisfiedBy(OptionGroup group) {
//        return optionGroupSpecs.stream().anyMatch(spec -> spec.isSatisfiedBy(group));
//    }



}
