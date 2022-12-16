package com.figo.dtos.cars;


import com.figo.dtos.base.DTO;
import com.figo.enums.CarStatus;
import lombok.NonNull;

public record CarCreateDTO(
        String carModel,

        String carNumber,

        Integer countOfPlace,

        String carColor,

        String carRegion,

        Integer carYear,

        String pricePerDay,

        String additionInfo
) implements DTO {
}
