package uz.master.warehouse.services;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.master.warehouse.validator.BaseValidator;
import uz.master.warehouse.mapper.BaseMapper;

/**
 * @param <R> Repository
 * @param <M> Mapper
 * @param <V> Validator
 */
public abstract class AbstractService<R extends JpaRepository, M extends BaseMapper, V extends BaseValidator> {

    protected R repository;
    protected M mapper;
    protected V validator;


    public AbstractService(R repository, M mapper, V validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }


}
