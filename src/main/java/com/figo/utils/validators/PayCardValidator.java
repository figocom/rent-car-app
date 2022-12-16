package com.figo.utils.validators;

import com.figo.dtos.paycards.CreateCardDTO;
import com.figo.dtos.paycards.PayCardDTO;

import java.util.Objects;

public class PayCardValidator extends AbstractValidator<CreateCardDTO, PayCardDTO, String>{
    @Override
    public void checkOnCreate(CreateCardDTO dto) throws IllegalArgumentException {
        if (Objects.isNull(dto.cardNumber()) || Objects.isNull(dto.balance()) || dto.cardNumber().length() != 16){
            throw new IllegalArgumentException("Bad credentials");
        }
        if (!dto.balance().toString().matches("[0-9]*[0-9]+\\$"))
            throw new IllegalArgumentException("Balance doesn't match");
    }
}
