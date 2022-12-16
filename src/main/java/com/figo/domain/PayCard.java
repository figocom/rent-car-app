package com.figo.domain;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@ToString
public class PayCard  extends BaseDomain{
    Integer userId;
    @NonNull
    String cardNumber;
    @NonNull
    Double balance;
    @Builder(builderMethodName = "childBuilder")
    public PayCard(String id, boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy,
                  Integer userId, @NonNull String cardNumber , @NonNull Double balance){
        super(id, deleted, createdAt, updatedAt, createdBy, updatedBy);
        this.userId=userId;
        this.cardNumber=cardNumber;
        this.balance=balance;
    }
}
