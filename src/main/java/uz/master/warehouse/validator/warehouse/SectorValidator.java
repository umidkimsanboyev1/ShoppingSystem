package uz.master.warehouse.validator.warehouse;

import org.springframework.stereotype.Component;
import uz.master.warehouse.annotations.HaveSector;
import uz.master.warehouse.services.wareHouse.SectorService;
import uz.master.warehouse.validator.BaseValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class SectorValidator implements BaseValidator, ConstraintValidator<HaveSector,Long> {

    private final SectorService service;

    public SectorValidator(SectorService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return service.get(aLong).isSuccess();
    }
}
