package com.figo.dtos.orders;

import com.figo.dtos.base.GenericDTO;
import com.figo.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@Getter
@Setter
@NonNull
@ToString
public class OrderUpdateDTO extends GenericDTO {
    private OrderStatus orderStatus;
    @Builder(builderMethodName = "childBuilder")
    public OrderUpdateDTO(String id ,  OrderStatus orderStatus){
        super(id);
        this.orderStatus=orderStatus;
    }
}
