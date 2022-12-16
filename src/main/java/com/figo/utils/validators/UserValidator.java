package com.figo.utils.validators;

import com.figo.daos.UserDAO;
import com.figo.dtos.users.UserCreateDTO;
import com.figo.dtos.users.UserUpdateDTO;
import lombok.NonNull;


import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class UserValidator extends AbstractValidator<UserCreateDTO, UserUpdateDTO, String> {


    @Override
    public void checkOnCreate(UserCreateDTO dto) throws IllegalArgumentException {
        if (Objects.isNull(dto))
            throw new IllegalArgumentException("DTO is null");
        if (!dto.phoneNumber().matches("\\d{9}")) {
            throw new IllegalArgumentException(" Number don't match");
        }
        List<String> values = List.of(dto.firstName(), dto.lastName(), dto.phoneNumber(), dto.username(), dto.region(), dto.address(), dto.password());
        if (isEmptyInput(values)) {
            throw new IllegalArgumentException("Some fields required");
        }
        checkStrongPassword(dto.password());
        UserDAO.checkUserNameAndPhone(dto.username(), dto.phoneNumber());


    }


    public void checkStrongPassword(@NonNull String password) throws IllegalArgumentException {
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        if (pattern.matcher(password).find()) {
            throw new IllegalArgumentException("Password should be strong!");
        }
    }

}

