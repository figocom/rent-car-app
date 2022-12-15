package com.figo.domain;


import com.figo.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
@Setter
@ToString
@NonNull
public class Order extends BaseDomain {
    Integer userId;
    Integer carId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    OrderStatus orderStatus;
    String passport;
    String driverLicense;
    String region;
    Double totalPrice;
    @Builder(builderMethodName = "childBuilder")
    public Order(String id, boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy,
               @NonNull Integer userId, @NonNull Integer carId, @NonNull LocalDateTime startTime, @NonNull LocalDateTime endTime, @NonNull String passport,
               @NonNull OrderStatus orderStatus, @NonNull String driverLicense, @NonNull String region ,@NonNull Double totalPrice) {
        super(id, deleted, createdAt, updatedAt, createdBy, updatedBy);
        this.userId=userId;
        this.carId=carId;
        this.startTime=startTime;
        this.endTime=endTime;
        this.orderStatus=orderStatus;
        this.passport=passport;
        this.driverLicense=driverLicense;
        this.region=region;
        this.totalPrice=totalPrice;
    }
}
