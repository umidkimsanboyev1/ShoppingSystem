package uz.master.warehouse.validator.organization;

import org.springframework.stereotype.Component;
import uz.master.warehouse.annotations.HaveFirm;
import uz.master.warehouse.services.organization.FirmService;
import uz.master.warehouse.validator.BaseValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Component
public class FirmValidator implements ConstraintValidator<HaveFirm, Long>, BaseValidator {

    private final FirmService service;

    public FirmValidator(FirmService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return service.get(aLong).isSuccess();
    }
}
