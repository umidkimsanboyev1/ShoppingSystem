package uz.master.warehouse.services;

import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;
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
        D extends BaseDto,
        CD extends GenericDto,
        UD extends GenericDto,
        K extends Serializable
        > extends GenericService<D, K> {

    K create(CD createDto);

    Void delete(K id);

    Void update(UD updateDto);

}
