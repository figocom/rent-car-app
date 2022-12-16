package com.figo.dtos.paycards;

import com.figo.dtos.base.DTO;

public record CreateCardDTO(Integer userId, String cardNumber, String balance) implements DTO {
}
