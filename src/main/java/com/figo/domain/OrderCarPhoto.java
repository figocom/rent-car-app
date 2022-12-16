package com.figo.domain;

import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OrderCarPhoto {
    Integer orderId;
    Car car;
    Order order;
    Photo photos;
    LocalDateTime createdTime;
    String userPhoneNumber;
}
