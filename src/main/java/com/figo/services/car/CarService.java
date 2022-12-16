package com.figo.services.car;

import com.figo.criteria.CarCriteria;

import com.figo.dtos.cars.CarCreateDTO;
import com.figo.dtos.cars.CarDTO;

import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.services.base.GenericCrudService;
import lombok.NonNull;

import java.util.List;

public interface CarService extends GenericCrudService<CarDTO, CarCreateDTO, CarDTO, String, CarCriteria> {
    Response<DataDTO<Boolean>> update(@NonNull List<CarDTO> dto , CarCriteria criteria);
    void check();
}
