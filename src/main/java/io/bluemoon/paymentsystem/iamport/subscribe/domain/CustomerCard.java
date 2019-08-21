package io.bluemoon.paymentsystem.iamport.subscribe.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
public class CustomerCard {

    @JsonProperty(value = "card_number")
    private String cardNumber;

    @JsonProperty(value = "expiry")
    private String expiry;

    @JsonProperty(value = "birth")
    private String birth;

    @JsonProperty(value = "pwd_2digit")
    private String pwd2Digit;


    @Builder
    public CustomerCard(String cardNumber, String expiry, String birth, String pwd2Digit) {
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.birth = birth;
        this.pwd2Digit = pwd2Digit;
    }

}
