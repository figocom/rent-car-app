package com.figo.utils.validators;

import com.figo.daos.CarDAO;
import com.figo.dtos.cars.CarCreateDTO;
import com.figo.dtos.cars.CarDTO;


import java.time.LocalDateTime;
import java.util.List;

public class CarValidator extends AbstractValidator<CarCreateDTO, CarDTO, String> {
    @Override
    public void checkOnCreate(CarCreateDTO dto) throws IllegalArgumentException {

        List<String>values= List.of(dto.carModel(),  dto.carColor(), dto.carNumber(), dto.carRegion(),dto.pricePerDay(), dto.pricePerDay(), String.valueOf(dto.carYear()));
        if (isEmptyInput(values)){
            throw new IllegalArgumentException("Some fields required");
        }
        if (!dto.carNumber().matches("\\d{2}[A-Z]\\d{3}[A-Z]{2}")){
            throw  new IllegalArgumentException("Car number don't match");
        }
        if (dto.carYear()> LocalDateTime.now().getYear()){
            throw  new IllegalArgumentException("Car year doesn't match");
        }
        if (!dto.pricePerDay().matches("[0-9]*[0-9]+\\$")){
            throw  new IllegalArgumentException("Car price doesn't match");
        }
        CarDAO.checkCarNumber(dto.carNumber());

    }
}
