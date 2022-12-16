package com.figo.dtos.paycards;

import com.figo.dtos.base.GenericDTO;

import lombok.*;

@NonNull
@Getter
@Setter
@NoArgsConstructor
public class PayCardDTO extends GenericDTO {
    private Integer userId;

    private String cardNumber;
    private Double balance;

    @Builder(builderMethodName = "childBuilder")
    public PayCardDTO(String id, Integer userId, String cardNumber, Double balance) {
        super(id);
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.balance = balance;
    }
}
