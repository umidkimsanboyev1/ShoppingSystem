package uz.master.warehouse.validator.organization;

import org.springframework.stereotype.Component;
import uz.master.warehouse.annotations.HaveCompany;
import uz.master.warehouse.dto.company.CompanyCreateDto;
import uz.master.warehouse.dto.company.CompanyUpdateDto;
import uz.master.warehouse.services.organization.CompanyService;
import uz.master.warehouse.validator.BaseValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Component
public class CompanyValidator implements ConstraintValidator<HaveCompany, Long>, BaseValidator {
    private final CompanyService service;

    public CompanyValidator(CompanyService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.nonNull(service.get(id));
    }

}
