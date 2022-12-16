package com.figo.mapper;

import com.figo.domain.PayCard;
import com.figo.dtos.paycards.CreateCardDTO;
import com.figo.dtos.paycards.PayCardDTO;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PayCardMapper  implements BaseMapper<PayCard, PayCardDTO, CreateCardDTO, PayCardDTO>{
    @Override
    public PayCard fromCreateDTO(@NonNull CreateCardDTO dto) {
        return PayCard.childBuilder().
                userId(dto.userId()).
                cardNumber(dto.cardNumber()).
                balance(Double.valueOf(dto.balance())).
                build();
    }

    @Override
    public PayCard fromUpdateDTO(@NonNull PayCardDTO dto) {
        return PayCard.childBuilder().
                userId(dto.getUserId()).
                cardNumber(dto.getCardNumber()).
                balance(dto.getBalance()).
                build();
    }

    @Override
    public PayCardDTO toDTO(@NonNull PayCard domain) {
        return PayCardDTO.childBuilder().
                userId(domain.getUserId()).
                cardNumber(domain.getCardNumber()).
                balance(domain.getBalance()).
                build();
    }

    @Override
    public List<PayCardDTO> toDTOs(@NonNull List<PayCard> domain) {
        List<PayCardDTO> payCardDTOS = new ArrayList<>();
        for (PayCard value : domain) {
            PayCardDTO payCardDTO = toDTO(value);
            payCardDTOS.add(payCardDTO);
        }
        return payCardDTOS;
    }
}
