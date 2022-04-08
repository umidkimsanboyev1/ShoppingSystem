package uz.master.warehouse.services;

import uz.master.warehouse.criteria.BaseCriteria;
import uz.master.warehouse.criteria.GenericCriteria;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;
import uz.master.warehouse.dto.responce.DataDto;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


/**
 * @param <D> DTO
 * @param <K> Id Long
 */
public interface GenericService<
        D extends BaseDto,
        K extends Serializable,
        C extends BaseCriteria
        > extends BaseService {


    DataDto<List<D>> getAll();

    DataDto<D> get(K id);

    DataDto<List<D>> getWithCriteria(C criteria) throws SQLException;
}
