package com.figo.services.car;

import com.figo.daos.CarDAO;
import com.figo.dtos.cars.CarCreateDTO;
import com.figo.mapper.CarMapper;
import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.utils.validators.CarValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceImplTest {
    CarDAO carDAO;
    CarMapper carMapper;
    CarValidator validator;
    CarService service;

    @BeforeEach
    void setUp() {
        carDAO=new CarDAO();
        carMapper =new CarMapper();
        validator =new CarValidator();
        service = new CarServiceImpl( carDAO,carMapper,validator);
    }
    @Test
   void checkThread(){
        service.check();
   }
    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        CarCreateDTO car= new CarCreateDTO("Tiko", "90A233AB",4,"red","Xorazm" , 2010 ,"10$", null);
        Response<DataDTO<String>> dataDTOResponse = service.create(car);
        Assertions.assertTrue(dataDTOResponse.data().isSuccess());
    }

    @Test
    void update() {

    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }
}