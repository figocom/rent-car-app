package com.figo.mapper;

import com.figo.domain.BaseDomain;
import com.figo.domain.Order;
import com.figo.dtos.orders.OrderCreateDTO;
import com.figo.dtos.orders.OrderDTO;
import com.figo.dtos.orders.OrderUpdateDTO;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper implements BaseMapper<Order, OrderDTO, OrderCreateDTO, OrderUpdateDTO> {
    @Override
    public Order fromCreateDTO(@NonNull OrderCreateDTO dto) {
        return Order.childBuilder()
                .userId(dto.userId())
                .carId(dto.carId())
                .region(dto.region())
                .orderStatus(dto.orderStatus())
                .totalPrice(dto.totalPrice())
                .passport(dto.passport())
                .driverLicense(dto.driverLicense())
                .build();
    }

    @Override
    public Order fromUpdateDTO(@NonNull OrderUpdateDTO dto) {
        return Order.childBuilder()
                .id(dto.getId())
                .orderStatus(dto.getOrderStatus())
                .build();
    }

    @Override
    public OrderDTO toDTO(@NonNull Order domain) {
        return OrderDTO.childBuilder()
                .userId(domain.getUserId())
                .carId(domain.getCarId())
                .region(domain.getRegion())
                .startTime(String.valueOf(domain.getStartTime()))
                .endTime(String.valueOf(domain.getEndTime()))
                .orderStatus(domain.getOrderStatus())
                .totalPrice(domain.getTotalPrice())
                .passport(domain.getPassport())
                .driverLicense(domain.getDriverLicense())
                .build();
    }

    @Override
    public List<OrderDTO> toDTOs(@NonNull List<Order> domain) {
        List<OrderDTO> orderDTOs=new ArrayList<>();
        for (Order order : domain) {
            OrderDTO orderDTO = toDTO(order);
            orderDTOs.add(orderDTO);
        }
        return orderDTOs;
    }
}
