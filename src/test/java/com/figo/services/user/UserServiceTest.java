package com.figo;

import com.figo.daos.UserDAO;
import com.figo.dtos.users.LoginRequestDTO;
import com.figo.dtos.users.UserCreateDTO;
import com.figo.dtos.users.UserDTO;
import com.figo.mapper.UserMapper;
import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.services.user.UserService;
import com.figo.services.user.UserServiceImpl;
import com.figo.utils.validators.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServiceTest {
    UserDAO dao;
    UserMapper mapper;
    UserValidator validator;

    UserService service;


    @BeforeEach
    void setUp() {
        dao = new UserDAO();
        validator = new UserValidator();
        mapper = new UserMapper();
        service = new UserServiceImpl(dao, mapper, validator);
    }

    @Test
    void registerUserTest() {
        UserCreateDTO dto = new UserCreateDTO("test" , "user",
                "934565645","root" ,"Navoiy" , "webpage" ,"Manguberdi@66");
        service.create(dto);
        dao.shutDownHook();
    }


    @Test
    void login_with_right_credentials_Test() {
        String username = "root";
        String password = "Manguberdi@66";
        LoginRequestDTO loginRequest = new LoginRequestDTO(username, password);
        Response<DataDTO<UserDTO>> response = service.login(loginRequest);
        DataDTO<UserDTO> data = response.data();
        System.out.println("data = " + data);
        Assertions.assertTrue(data.isSuccess());
    }

    @Test
    void login_with_bad_credentials_Test() {
        String username = "jl";
        String password = "1234";
        LoginRequestDTO loginRequest = new LoginRequestDTO(username, password);
        Response<DataDTO<UserDTO>> response = service.login(loginRequest);
        DataDTO<UserDTO> data = response.data();
        System.out.println(data.getError());
        Assertions.assertFalse(data.isSuccess());

    }
}
