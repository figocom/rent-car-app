package com.figo.utils;

import com.figo.daos.CarDAO;
import com.figo.daos.UserDAO;
import com.figo.domain.Car;
import com.figo.mapper.CarMapper;
import com.figo.mapper.UserMapper;
import com.figo.services.car.CarService;
import com.figo.services.car.CarServiceImpl;
import com.figo.services.user.UserService;
import com.figo.services.user.UserServiceImpl;
import com.figo.utils.validators.CarValidator;
import com.figo.utils.validators.UserValidator;

public class CarUtil {
    public  static CarService getService() {
        CarDAO dao = new CarDAO();
        CarMapper mapper = new CarMapper();
        CarValidator validator = new CarValidator();
        return new CarServiceImpl(dao, mapper, validator);
    }
}
