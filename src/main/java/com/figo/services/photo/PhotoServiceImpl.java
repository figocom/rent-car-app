package com.figo.services.photo;

import com.figo.criteria.PhotoCriteria;
import com.figo.criteria.UserCriteria;
import com.figo.daos.CarDAO;
import com.figo.daos.PhotoDAO;
import com.figo.domain.Photo;
import com.figo.dtos.photos.PhotoDTO;
import com.figo.dtos.users.UserDTO;
import com.figo.mapper.CarMapper;
import com.figo.mapper.PhotoMapper;
import com.figo.response.DataDTO;
import com.figo.response.ErrorDTO;
import com.figo.response.Response;
import com.figo.services.base.AbstractService;
import com.figo.services.car.CarService;
import com.figo.utils.validators.CarValidator;
import com.figo.utils.validators.PhotoValidator;
import lombok.NonNull;

import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.logging.Logger;

public class PhotoServiceImpl extends AbstractService<PhotoDAO, PhotoMapper, PhotoValidator> implements PhotoService {
    private final Logger logger = Logger.getLogger(getClass().getName());
    public PhotoServiceImpl(PhotoDAO dao, PhotoMapper mapper, PhotoValidator validator) {
        super(dao, mapper, validator);
    }



    @Override
    public Response<DataDTO<String>> create(@NonNull PhotoDTO dto) {
        try {
            validator.checkOnCreate(dto);
            Photo photo = mapper.fromCreateDTO(dto);
            dao.save(photo);
            return  new Response<>(new DataDTO<>(true));
        }
        catch (IllegalArgumentException e){
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }

    @Override
    public Response<DataDTO<Boolean>> update(@NonNull PhotoDTO dto) {
        return null;
    }

    @Override
    public Response<DataDTO<Boolean>> delete(@NonNull String s) {
        return null;
    }

    @Override
    public Response<DataDTO<List<PhotoDTO>>> getAll() {
        return null;
    }

    @Override
    public Response<DataDTO<PhotoDTO>> get(@NonNull String s) {
        return null;
    }

    @Override
    public Response<DataDTO<List<PhotoDTO>>> getAll(@NonNull PhotoCriteria criteria) {
        try {
            List<Photo> photos = dao.getPhotos(criteria.getCarNumbers());
            List<PhotoDTO> photoDTOS = mapper.toDTOs(photos);
            return new Response<>(new DataDTO<>(photoDTOS));
        }
        catch (IllegalArgumentException e){
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }
}