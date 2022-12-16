package com.figo.services.paycard;

import com.figo.criteria.PayCardCriteria;
import com.figo.daos.PayCardDAO;
import com.figo.domain.PayCard;
import com.figo.dtos.paycards.CreateCardDTO;
import com.figo.dtos.paycards.PayCardDTO;
import com.figo.mapper.PayCardMapper;
import com.figo.response.DataDTO;
import com.figo.response.ErrorDTO;
import com.figo.response.Response;
import com.figo.services.base.AbstractService;
import com.figo.utils.validators.PayCardValidator;
import lombok.NonNull;

import java.util.List;
import java.util.logging.Logger;

public class PayCardServiceImpl extends AbstractService<PayCardDAO, PayCardMapper, PayCardValidator> implements PayCardService {
    public PayCardServiceImpl(PayCardDAO dao, PayCardMapper mapper, PayCardValidator validator) {
        super(dao, mapper, validator);
    }
    private final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public Response<DataDTO<String>> create(@NonNull CreateCardDTO dto) {
        try {
            validator.checkOnCreate(dto);
            String balance = dto.balance().substring(0, dto.balance().indexOf("$"));
            CreateCardDTO cardDTO= new CreateCardDTO(dto.userId(), dto.cardNumber(),balance );
            PayCard payCard = mapper.fromCreateDTO(cardDTO);
            dao.addCard(payCard);
            return  new Response<>(new DataDTO<>("Successfully added"));
        }catch (IllegalArgumentException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }

    }

    @Override
    public Response<DataDTO<Boolean>> update(@NonNull PayCardDTO dto) {
        try {
            PayCard card = mapper.fromUpdateDTO(dto);
           Boolean update =dao.payToOrder(card);
            return new Response<>(new DataDTO<>(update));
        }catch (IllegalArgumentException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }

    @Override
    public Response<DataDTO<Boolean>> delete(@NonNull String cardNumber) {
        try {
            Boolean success=dao.deleteCard(cardNumber);
            return new Response<>(new DataDTO<>(success));
        }catch (IllegalArgumentException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }

    }

    @Override
    public Response<DataDTO<List<PayCardDTO>>> getAll() {
        return null;
    }

    @Override
    public Response<DataDTO<PayCardDTO>> get(@NonNull String s) {
        try {
            PayCard card=dao.getCard(s);
            PayCardDTO payCardDTO = mapper.toDTO(card);
            return new Response<>(new DataDTO<>(payCardDTO));
        }catch (IllegalArgumentException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }

    @Override
    public Response<DataDTO<List<PayCardDTO>>> getAll(@NonNull PayCardCriteria criteria) {
        try {
           List<PayCardDTO> payCardDTOS=dao.getUserCards(criteria.getUserId());
           return new Response<>(new DataDTO<>(payCardDTOS));
        }catch (IllegalArgumentException e) {
            logger.severe(e.getLocalizedMessage());
            ErrorDTO error = new ErrorDTO(e.getMessage());
            return new Response<>(new DataDTO<>(error));
        }
    }
}
