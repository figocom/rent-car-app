package com.figo.utils;

import com.figo.daos.PayCardDAO;
import com.figo.mapper.PayCardMapper;
import com.figo.services.paycard.PayCardService;
import com.figo.services.paycard.PayCardServiceImpl;
import com.figo.utils.validators.PayCardValidator;

public class PayCardUtil {
    public  static PayCardService getService() {
        PayCardDAO dao = new PayCardDAO();
        PayCardMapper mapper = new PayCardMapper();
        PayCardValidator validator = new PayCardValidator();
        return new PayCardServiceImpl(dao, mapper, validator);
    }
}
