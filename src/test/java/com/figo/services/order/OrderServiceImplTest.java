package com.figo.services.order;

import com.figo.daos.OrderDAO;
import com.figo.dtos.orders.OrderCreateDTO;
import com.figo.enums.OrderStatus;
import com.figo.mapper.OrderMapper;
import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.utils.validators.OrderValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    OrderDAO orderDAO;
    OrderMapper orderMapper;
    OrderValidator validator;
    OrderService service;

    @BeforeEach
    void setUp() {
        orderDAO=new OrderDAO();
        orderMapper =new OrderMapper();
        validator =new OrderValidator();
        service = new OrderServiceImpl( orderDAO,orderMapper,validator);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        Response<DataDTO<String>> dataDTOResponse = service.create(new OrderCreateDTO(10, 8, "2022-12-14 00:00:00", "2022-12-15 00:00:00",
                OrderStatus.REQUESTED, "AA2345676", "AA2345676", "Navoiy", 0.0));
        Assertions.assertTrue(dataDTOResponse.data().isSuccess());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }
}