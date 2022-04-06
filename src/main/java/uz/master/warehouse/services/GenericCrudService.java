package uz.master.warehouse.services;

import uz.master.warehouse.criteria.BaseCriteria;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;
import uz.master.warehouse.dto.responce.DataDto;
import uz.master.warehouse.entity.base.BaseEntity;

import java.io.Serializable;


/**
 * @param <E>  -> Entity
 * @param <D>  -> Dto
 * @param <CD> -> Create Dto
 * @param <UD> -> Update Dto
 * @param <K>  -> class that defines the primary key for your pojo class
 */
public interface GenericCrudService<
        E extends BaseEntity,
        D extends GenericDto,
        CD extends BaseDto,
        UD extends GenericDto,
        C extends BaseCriteria,
        K extends Serializable
        > extends GenericService<D, K, C> {

    DataDto<K> create(CD createDto);

    DataDto<Void> delete(K id);

    DataDto<K> update(UD updateDto);

}
