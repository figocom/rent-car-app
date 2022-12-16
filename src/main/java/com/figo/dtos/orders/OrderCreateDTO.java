package com.figo.dtos.orders;

import com.figo.dtos.base.DTO;
import com.figo.enums.OrderStatus;

import java.time.LocalDateTime;

public record OrderCreateDTO(
        Integer userId,
        Integer carId,
        String startTime,
        String endTime,
        OrderStatus orderStatus,
        String passport,
        String driverLicense,
        String region,
        Double totalPrice
        ) implements DTO {
}
