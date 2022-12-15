package com.figo.domain;

import com.figo.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@ToString
@NonNull
public class CarOrder extends BaseDomain{
    Integer orderId;
    Car car;
    Order order;
    Photo photos;
    String userPhoneNumber;
    @Builder(builderMethodName = "childBuilder")
    public CarOrder(String id, boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy,
                    @NonNull Integer orderId , @NonNull Car car , @NonNull Order order, @NonNull Photo photos, @NonNull String userPhoneNumber ){
        super(id, deleted, createdAt, updatedAt, createdBy, updatedBy);
        this.orderId=orderId;
        this.car=car;
        this.order=order;
        this.photos=photos;
        this.userPhoneNumber=userPhoneNumber;
    }
}
