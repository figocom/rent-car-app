package com.figo.services.photo;


import com.figo.daos.PhotoDAO;

import com.figo.dtos.cars.CarCreateDTO;
import com.figo.dtos.photos.PhotoDTO;
import com.figo.mapper.PhotoMapper;

import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.utils.validators.PhotoValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


class PhotoServiceImplTest {
    PhotoDAO photoDAO;
    PhotoMapper photoMapper;
    PhotoValidator validator;
    PhotoService service;

    @BeforeEach
    void setUp() {
        photoDAO=new PhotoDAO();
        photoMapper =new PhotoMapper();
        validator =new PhotoValidator();
        service = new PhotoServiceImpl( photoDAO,photoMapper,validator);
    }

    @Test
    void create() {
        PhotoDTO car=PhotoDTO.childBuilder().urls(List.of("D:/Javalessons/java review git/rent-car-app/src/main/webapp/carImages/Avtobus biletManguberdief663fef-1b88-4871-8297-ca52ccdc2bc7.png",
                "D:/Javalessons/java review git/rent-car-app/src/main/webapp/carImages/Avtobus biletManguberdief663fef-1b88-4871-8297-ca52ccdc2bc8.png")).
                carNumber("10A222BB").build();
        Response<DataDTO<String>> dataDTOResponse = service.create(car);
        Assertions.assertTrue(dataDTOResponse.data().isSuccess());
    }

    @Test
    void delete() {
    }

    @Test
    void get() {
    }

    @Test
    void getAll() {
    }
}