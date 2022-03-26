package uz.master.warehouse.validator;

import lombok.Getter;
import uz.master.warehouse.dto.BaseDto;
import uz.master.warehouse.dto.GenericDto;

/**
 * @param <CD>Create Dto
 * @param <UD>Update Dto
 */


public interface GenericValidator<
        CD extends BaseDto,
        UD extends GenericDto> extends BaseValidator{

    boolean validForCreate(CD createDto);

    boolean validForUpdate(UD updateDto);
}
