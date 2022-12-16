package com.figo.services.base;

import com.figo.criteria.GenericCriteria;
import com.figo.dtos.base.DTO;
import com.figo.response.DataDTO;
import com.figo.response.Response;
import lombok.NonNull;


import java.io.Serializable;
import java.util.List;

/**
 *
 * @param <D>
 * @param <ID>
 * @param <C>
 */
public interface GenericService<D extends DTO, ID extends Serializable, C extends GenericCriteria> {
    Response<DataDTO<D>> get(@NonNull ID id);

    Response<DataDTO<List<D>>> getAll(@NonNull C criteria);
}
