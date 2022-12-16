package com.figo.dtos.users;


import com.figo.dtos.base.GenericDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserUpdateDTO  extends GenericDTO{
    String phoneNumber;
    boolean addToAdmins;
}
