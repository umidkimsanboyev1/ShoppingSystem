package uz.master.warehouse.validator.organization;

import org.springframework.stereotype.Component;
import uz.master.warehouse.annotations.HaveMarket;
import uz.master.warehouse.services.organization.MarketService;
import uz.master.warehouse.validator.BaseValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component

public class MarketValidator implements ConstraintValidator<HaveMarket, Long>, BaseValidator {
    private final MarketService service;

    public MarketValidator(MarketService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return service.get(id).isSuccess();
    }
}
