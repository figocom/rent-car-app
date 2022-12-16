package com.figo.mapper;

import com.figo.domain.Car;

import com.figo.dtos.cars.CarCreateDTO;
import com.figo.dtos.cars.CarDTO;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CarMapper implements BaseMapper<Car, CarDTO, CarCreateDTO, CarDTO> {
    @Override
    public Car fromCreateDTO(@NonNull CarCreateDTO dto) {
        return Car.childBuilder().
                carModel(dto.carModel()).
                carNumber(dto.carNumber()).
                carRegion(dto.carRegion()).
                carColor(dto.carColor()).
                countOfPlace(dto.countOfPlace()).
                carYear(dto.carYear()).
                pricePerDay(dto.pricePerDay()).
                additionInfo(dto.additionInfo()).
                build();
    }

    @Override
    public Car fromUpdateDTO(@NonNull CarDTO dto) {
        return null;
    }


    @Override
    public CarDTO toDTO(@NonNull Car domain) {
        return CarDTO.childBuilder()
                .id(domain.getId())
                .carModel(domain.getCarModel())
                .carNumber(domain.getCarNumber())
                .carStatus(String.valueOf(domain.getCarStatus()))
                .carRegion(domain.getCarRegion())
                .carColor(domain.getCarColor())
                .countOfPlace(String.valueOf(domain.getCountOfPlace()))
                .carYear(String.valueOf(domain.getCarYear()))
                .pricePerDay(domain.getPricePerDay())
                .additionInfo(domain.getAdditionInfo())
                .deleted(domain.isDeleted())
                .build();
    }

    @Override
    public List<CarDTO> toDTOs(@NonNull List<Car> domain) {
        List<CarDTO>carDTOs=new ArrayList<>();
        for (Car car : domain) {
            CarDTO carDTO = toDTO(car);
            carDTOs.add(carDTO);
        }
        return carDTOs;
    }

}
