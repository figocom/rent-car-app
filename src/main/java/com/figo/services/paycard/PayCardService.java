package com.figo.services.paycard;

import com.figo.criteria.PayCardCriteria;
import com.figo.dtos.paycards.CreateCardDTO;
import com.figo.dtos.paycards.PayCardDTO;
import com.figo.services.base.GenericCrudService;

public interface PayCardService  extends GenericCrudService<PayCardDTO, CreateCardDTO,PayCardDTO, String , PayCardCriteria> {
}
