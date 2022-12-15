package com.figo.daos;


import com.figo.domain.Entity;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
