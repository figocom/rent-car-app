package com.figo.dtos.users;


import com.figo.dtos.base.DTO;

public record UserCreateDTO(String firstName,
                            String lastName,
                            String phoneNumber,
                            String username,
                            String region,
                            String address,
                            String password) implements DTO {
}
