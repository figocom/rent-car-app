package com.figo.services.user;


import com.figo.criteria.UserCriteria;
import com.figo.daos.UserDAO;
import com.figo.domain.User;
import com.figo.dtos.users.LoginRequestDTO;
import com.figo.dtos.users.UserCreateDTO;
import com.figo.dtos.users.UserDTO;
import com.figo.dtos.users.UserUpdateDTO;
import com.figo.mapper.UserMapper;
import com.figo.response.DataDTO;
import com.figo.response.ErrorDTO;
import com.figo.response.Response;
import com.figo.services.base.AbstractService;
import com.figo.utils.validators.UserValidator;
import lombok.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class UserServiceImpl extends AbstractService<UserDAO, UserMapper, UserValidator> implements UserService {

    private final Logger logger = Logger.getLogger(getClass().getName());

    public UserServiceImpl(UserDAO dao, UserMapper mapper, UserValidator validator) {
        super(dao, mapper, validator);
    }

    @Override
    public Response<DataDTO<List<UserDTO>>> getAll() {
        return null;
    }

    @Override
    public Response<DataDTO<String>> create(@NonNull UserCreateDTO dto) {
        try {
            validator.checkOnCreate(dto);
            User user = mapper.fromCreateDTO(dto);
            user.setPassword(util.encode(user.getPassword()));
            dao.registerUser(user);
            return new Response<>(new DataDTO<>(user.getId()));
        } catch (IllegalArgumentException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }

    @Override
    public Response<DataDTO<Boolean>> update(@NonNull UserUpdateDTO dto) {
        try {
            Boolean updated = dao.updateUser(dto);
            return new Response<>(new DataDTO<>(updated));
        } catch (IllegalArgumentException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }

    @Override
    public Response<DataDTO<Boolean>> delete(@NonNull String s) {
        return null;
    }

    @Override
    public Response<DataDTO<UserDTO>> get(@NonNull String s) {
        try {
            User user = dao.findByUsername(s);
            UserDTO userDTO = mapper.toDTO(user);
            return new Response<>(new DataDTO<>(userDTO));
        } catch (IllegalArgumentException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }

    @Override
    public Response<DataDTO<List<UserDTO>>> getAll(@NonNull UserCriteria criteria) {
        try {
            List<User> admins = dao.getUsersList(criteria);
            List<UserDTO> userDTOS = mapper.toDTOs(admins);
            return new Response<>(new DataDTO<>(userDTOS));
        }catch (IllegalArgumentException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }

    @Override
    public Response<DataDTO<UserDTO>> login(@NonNull LoginRequestDTO loginRequest) {
        try {
            User user = dao.findByUsername(loginRequest.username());
            if (Objects.isNull(user.getPassword()))   return new Response<>(new DataDTO<>(new ErrorDTO("Bad credentials")));
            if (!util.match(loginRequest.password(), user.getPassword()))
                return new Response<>(new DataDTO<>(new ErrorDTO("Bad credentials")));
            UserDTO userDTO = mapper.toDTO(user);
            return new Response<>(new DataDTO<>(userDTO));
        } catch (RuntimeException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }

    @Override
    public Response<DataDTO<Boolean>> userIsAdmin(UserDTO userDTO) {
        try {
            Boolean isAdmin = dao.userIsAdmin(userDTO);
            return new Response<>(new DataDTO<>(isAdmin));
        }
        catch (RuntimeException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }
}
