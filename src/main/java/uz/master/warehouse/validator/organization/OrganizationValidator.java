package uz.master.warehouse.validator.organization;

import org.springframework.stereotype.Component;
import uz.master.warehouse.annotations.HaveOrg;
import uz.master.warehouse.services.organization.OrganizationService;
import uz.master.warehouse.validator.BaseValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Component
public class OrganizationValidator implements ConstraintValidator<HaveOrg, Long>, BaseValidator {

    private final OrganizationService service;

    public OrganizationValidator(OrganizationService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return service.get(id).isSuccess();
    }
}
