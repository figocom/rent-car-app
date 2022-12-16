package com.figo.utils.validators;

import com.figo.dtos.base.DTO;
import com.figo.dtos.base.GenericDTO;


import java.io.Serializable;
import java.util.List;

public abstract class AbstractValidator<CD extends DTO,
        UD extends GenericDTO,
        ID extends Serializable> implements BaseValidator {

    public void checkOnCreate(CD dto) throws IllegalArgumentException {

    }

    public void checkOnUpdate(UD dto) throws IllegalArgumentException {

    }

    public void checkID(ID id) throws IllegalArgumentException {

    }
    public  boolean isEmptyInput(List<String> values){
        for (String value : values) {
            if (value.isEmpty() || value.isBlank()) {
                return true;
            }
        }
        return false;
    }

}
