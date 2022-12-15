package com.figo.domain;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@ToString
public class PayCard  extends BaseDomain{
    @NonNull
    Integer cardNumber;
    @NonNull
    Double balance;
    @Builder(builderMethodName = "childBuilder")
    public PayCard(String id, boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy,
                   @NonNull Integer cardNumber , @NonNull Double balance){
        super(id, deleted, createdAt, updatedAt, createdBy, updatedBy);
        this.cardNumber=cardNumber;
        this.balance=balance;
    }
}
