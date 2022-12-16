package com.figo.mapper;

import com.figo.domain.User;
import com.figo.dtos.users.UserCreateDTO;
import lombok.NonNull;

import java.util.List;

public interface BaseMapper<T,D ,CD, UD> extends Mapper {
    T fromCreateDTO(@NonNull CD dto);



    T fromUpdateDTO(@NonNull UD dto);

    D toDTO(@NonNull T domain);

    List<D> toDTOs(@NonNull List<T> domain);
}
