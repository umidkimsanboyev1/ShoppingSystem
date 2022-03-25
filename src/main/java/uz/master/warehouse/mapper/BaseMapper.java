package uz.master.warehouse.mapper;

import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;
import uz.master.warehouse.entity.base.BaseEntity;

import java.util.List;

/**
 * @param <E>  -> Entity
 * @param <D>  -> Dto
 * @param <CD> -> Create Dto
 * @param <UD> -> Update Dto
 */

public interface BaseMapper<
        E extends BaseEntity,
        D extends GenericDto,
        CD extends BaseDto,
        UD extends GenericDto>{

    D toDto(E e);

    List<D> toDto(List<E> e);

    E fromCreateDto(CD cd);

    E fromUpdateDto(UD ud);

}