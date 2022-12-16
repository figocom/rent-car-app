package com.figo.services.base;


import com.figo.criteria.GenericCriteria;
import com.figo.dtos.base.DTO;
import com.figo.dtos.base.GenericDTO;
import com.figo.dtos.users.UserDTO;
import com.figo.response.DataDTO;
import com.figo.response.Response;
import lombok.NonNull;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @param <D> DTO -> data transfer object
 * @param <CD> Create DTO for creating domain in db
 * @param <UD> Update DTO for updating specific domain
 * @param <ID> Id
 * @param <C> Criteria
 */
public interface GenericCrudService<
        D extends GenericDTO,
        CD extends DTO,
        UD extends GenericDTO,
        ID extends Serializable,
        C extends GenericCriteria>
        extends GenericService<D, ID, C> {
    Response<DataDTO<ID>> create(@NonNull CD dto);

    Response<DataDTO<Boolean>> update(@NonNull UD dto);


    Response<DataDTO<Boolean>> delete(@NonNull ID id);
     Response<DataDTO<List<D>>> getAll();
}
