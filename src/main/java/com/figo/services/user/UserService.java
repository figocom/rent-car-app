package com.figo.services.user;

import com.figo.criteria.UserCriteria;
import com.figo.dtos.users.LoginRequestDTO;
import com.figo.dtos.users.UserCreateDTO;
import com.figo.dtos.users.UserDTO;
import com.figo.dtos.users.UserUpdateDTO;
import com.figo.response.DataDTO;
import com.figo.response.Response;
import com.figo.services.base.GenericCrudService;
import lombok.NonNull;


public interface UserService extends GenericCrudService<UserDTO, UserCreateDTO, UserUpdateDTO, String, UserCriteria> {
    Response<DataDTO<UserDTO>> login(@NonNull LoginRequestDTO loginRequest);

    Response<DataDTO<Boolean>>  userIsAdmin(UserDTO userDTO);
}
