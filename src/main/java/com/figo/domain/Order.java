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
    private Integer userId;
    private Integer carId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private OrderStatus orderStatus;
    private String passport;
    private String driverLicense;
    private String region;
    private Double totalPrice;

    @Builder(builderMethodName = "childBuilder")
    public Order(String id, boolean deleted, LocalDateTime createdAt, LocalDateTime updatedAt, String createdBy, String updatedBy,
                 @NonNull Integer userId, @NonNull Integer carId, LocalDateTime startTime, LocalDateTime endTime, @NonNull String passport,
                 @NonNull OrderStatus orderStatus, @NonNull String driverLicense, @NonNull String region, @NonNull Double totalPrice) {
        super(id, deleted, createdAt, updatedAt, createdBy, updatedBy);
        this.userId = userId;
        this.carId = carId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.orderStatus = orderStatus;
        this.passport = passport;
        this.driverLicense = driverLicense;
        this.region = region;
        this.totalPrice = totalPrice;
    }
}
