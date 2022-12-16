package com.figo.daos;


import com.figo.domain.Entity;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @param <T> T -> Domain
 */
public abstract class GenericDAO<T extends Entity> {

    private final Class<T> persistentClass;
    private final String className;




    @SuppressWarnings("unchecked")
    protected GenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        this.className = persistentClass.getSimpleName();


    }











}
