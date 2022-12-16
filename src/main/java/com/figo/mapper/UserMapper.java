package com.figo.mapper;


import com.figo.domain.User;
import com.figo.dtos.users.UserCreateDTO;
import com.figo.dtos.users.UserDTO;
import com.figo.dtos.users.UserUpdateDTO;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class UserMapper implements BaseMapper<User, UserDTO, UserCreateDTO, UserUpdateDTO> {
    @Override
    public User fromCreateDTO(@NonNull UserCreateDTO dto) {
        return User.childBuilder().
                lastName(dto.lastName()).
                firstName(dto.firstName()).
                phoneNumber(dto.phoneNumber()).
                username(dto.username()).
                region(dto.region()).
                address(dto.address()).
                password(dto.password()).
                build();
    }

    @Override
    public User fromUpdateDTO(@NonNull UserUpdateDTO dto) {
        return null;
    }

    @Override
    public UserDTO toDTO(@NonNull User domain) {
        return UserDTO.childBuilder()
                .id(domain.getId())
                .firstName(domain.getFirstName())
                .lastName(domain.getLastName())
                .username(domain.getUsername())
                .password(null)
                .phoneNumber(domain.getPhoneNumber())
                .region(domain.getRegion())
                .address(domain.getAddress())
                .isAdmin(domain.isAdmin())
                .deleted(domain.isDeleted())
                .build();
    }

    @Override
    public List<UserDTO> toDTOs(@NonNull List<User> domains) {
        List<UserDTO> userDTOList=new ArrayList<>();
        for (User domain : domains) {
            UserDTO userDTO = toDTO(domain);
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }
}
