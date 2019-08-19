package io.bluemoon.paymentsystem.generic.money.infra;

import io.bluemoon.paymentsystem.generic.money.domain.Money;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Long> {
    @Override
    public Long convertToDatabaseColumn(Money attribute) {
        return attribute.getAmount().longValue();
    }

    @Override
    public Money convertToEntityAttribute(Long dbData) {
        return Money.wons(dbData);
    }
}
