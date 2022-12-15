package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Penalty {
    private int orderId;
    private int userId;
    private String carModel;
    private String carNumber;
    private String info;
    private String amount;
}
