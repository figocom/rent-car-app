package com.figo.services.paycard;

import com.figo.daos.PayCardDAO;
import com.figo.dtos.paycards.CreateCardDTO;
import com.figo.mapper.PayCardMapper;
import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.utils.validators.PayCardValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PayCardServiceImplTest {

    PayCardDAO dao;
    PayCardMapper mapper;
    PayCardValidator validator;
    PayCardService service;

    @BeforeEach
    void setUp() {
        dao=new PayCardDAO();
        mapper =new PayCardMapper();
        validator =new PayCardValidator();
        service = new PayCardServiceImpl( dao,mapper,validator);
    }

    @Test
    void create() {
        Response<DataDTO<String>> dataDTOResponse = service.create(new CreateCardDTO(11, "1234567891234568", "874$"));
        Assertions.assertTrue(dataDTOResponse.data().isSuccess());
    }

    @Test
    void delete() {
        Response<DataDTO<Boolean>> delete = service.delete("1234567891234568");
        Assertions.assertTrue(delete.data().getData());
    }

    @Test
    void getAll() {
    }
}