package io.bluemoon.paymentsystem.plan.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "option_group_spec")
@Getter
public class OptionGroupSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_group_spec_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "exclusive")
    private boolean exclusive;

    @Column(name = "basic")
    private boolean basic;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "option_group_spec_id")
    private List<OptionSpecification> optionSpecs = new ArrayList<>();

    @Builder
    public OptionGroupSpecification(Long id, String name, boolean exclusive, boolean basic, List<OptionSpecification> options) {
        this.id = id;
        this.name = name;
        this.exclusive = exclusive;
        this.basic = basic;
        this.optionSpecs.addAll(options);
    }
    OptionGroupSpecification() {

    }
    public OptionGroupSpecification(String name, boolean exclusive, boolean basic, OptionSpecification... options) {
        this(null, name, exclusive, basic, Arrays.asList(options));
    }

    // ... 은 같은 값을 리스트로 받는것!
    public static OptionGroupSpecification basic(String name, boolean exclusive, OptionSpecification... options) {
        return new OptionGroupSpecification(name, exclusive, true, options);
    }

    public String getName() {
        return name;
    }

    private List<Option> satisfied(List<Option> options) {
        return optionSpecs
                .stream()
                .flatMap(spec -> options.stream().filter(spec::isSatisfiedBy))
                .collect(Collectors.toList());
    }

    private boolean isSatisfied(String groupName, List<Option> satisfied) {
        if (!name.equals(groupName)) {
            return false;
        }

        if (satisfied.isEmpty()) {
            return false;
        }

        if (exclusive && satisfied.size() > 1) {
            return false;
        }

        return true;
    }

    public boolean isSatisfiedBy(OptionGroup optionGroup) {
        return !isSatisfied(optionGroup.getName(), satisfied(optionGroup.getOptions()));
    }







}
