package com.figo.dtos.orders;

import com.figo.dtos.base.GenericDTO;
import com.figo.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
@NonNull
@Getter
@Setter
@NoArgsConstructor
public class OrderDTO extends GenericDTO {
    private Integer userId;
    private Integer carId;
    private String startTime;
    private String endTime;
    private OrderStatus orderStatus;
    private String passport;
    private String driverLicense;
    private String region;
    private Double totalPrice;
    @Builder(builderMethodName = "childBuilder")
    public OrderDTO(String id , Integer userId , Integer carId , String startTime , String endTime, OrderStatus orderStatus,
                     String passport , String driverLicense , String region , Double totalPrice){
        super(id);
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
