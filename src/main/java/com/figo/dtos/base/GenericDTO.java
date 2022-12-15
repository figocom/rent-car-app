package com.figo.rentcar.dtos.base;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenericDTO implements DTO {
    private String id;
}
