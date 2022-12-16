package com.figo.utils.validators;

import com.figo.daos.OrderDAO;
import com.figo.domain.Order;
import com.figo.dtos.orders.OrderCreateDTO;
import com.figo.dtos.orders.OrderUpdateDTO;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

public class OrderValidator extends AbstractValidator<OrderCreateDTO , OrderUpdateDTO , String>{
    @Override
    public void checkOnCreate(@NonNull OrderCreateDTO dto) throws IllegalArgumentException {
        List<String> values= List.of(dto.startTime() , dto.endTime(),dto.passport() ,dto.driverLicense());
        if (isEmptyInput(values)){
            throw new IllegalArgumentException("Some fields required");
        }
        if (dto.passport().matches("\\[A-Z]{2}\\d{7}"))
            throw  new IllegalArgumentException("Passport doesn't match!");
        if (dto.driverLicense().matches("\\[A-Z]{2}\\d{7}"))
            throw  new IllegalArgumentException("Driver license doesn't match!");

        if (OrderDAO.userHaveAvailableOrder(dto.passport() ,dto.driverLicense())) {
            throw new IllegalArgumentException("You have got available order!");
        }
    }
    public  void checkTime(Order order)throws IllegalArgumentException {

        if (order.getEndTime().isBefore(order.getStartTime())|| order.getStartTime().isBefore(LocalDateTime.now()) ||order.getStartTime().isEqual(order.getEndTime())) {
            throw  new IllegalArgumentException("Time doesn't match!");
        }
    }
}
