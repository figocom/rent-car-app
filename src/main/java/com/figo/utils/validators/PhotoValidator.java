package com.figo.utils.validators;

import com.figo.daos.CarDAO;
import com.figo.dtos.photos.PhotoDTO;

import java.util.Objects;

public class PhotoValidator extends AbstractValidator<PhotoDTO, PhotoDTO, String> {
    @Override
    public void checkOnCreate(PhotoDTO dto) throws IllegalArgumentException {

        CarDAO.getCarId(dto.getCarNumber());


    }
}
