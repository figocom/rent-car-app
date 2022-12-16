package com.figo.services.base;


import com.figo.daos.AbstractDAO;
import com.figo.mapper.Mapper;
import com.figo.utils.serviceutil.BaseUtil;
import com.figo.utils.validators.BaseValidator;


public abstract class AbstractService<R extends AbstractDAO,
        M extends Mapper,V extends BaseValidator> {

    protected final R dao;
    protected final M mapper;
    protected final V validator;
    protected final BaseUtil util;

    public AbstractService(R dao, M mapper, V validator) {
        this.validator = validator;

        this.dao = dao;
        this.mapper = mapper;
        this.util = new BaseUtil();
    }


}
