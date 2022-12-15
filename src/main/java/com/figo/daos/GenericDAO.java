package com.figo.rentcar.daos;


import com.figo.rentcar.domain.Entity;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @param <T> T -> Domain
 */
public abstract class GenericDAO<T extends Entity> {

    private final Class<T> persistentClass;
    private final String className;
    protected final List<T> data;



    @SuppressWarnings("unchecked")
    protected GenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        this.className = persistentClass.getSimpleName();
        this.data = loadData();

    }


    public T save(T t) {
        data.add(t);
        return t;
    }

    public void shutDownHook() {

    }


    private List<T> loadData() {
        //todo
    return  null;
    }




}
