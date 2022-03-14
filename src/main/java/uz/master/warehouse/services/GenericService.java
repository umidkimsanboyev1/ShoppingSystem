package uz.master.warehouse.services;

import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

import java.io.Serializable;
import java.util.List;


/**
 * @param <D> DTO
 * @param <K> Id Long
 */
public interface GenericService<
        D extends BaseDto,
        K extends Serializable
        > extends BaseService {


    List<D> getAll();

    D get(K id);
}