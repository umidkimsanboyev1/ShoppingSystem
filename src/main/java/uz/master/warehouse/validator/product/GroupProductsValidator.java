package uz.master.warehouse.validator.product;

import org.springframework.stereotype.Component;
import uz.master.warehouse.annotations.HaveGroupProducts;
import uz.master.warehouse.services.product.GroupProductsService;
import uz.master.warehouse.validator.BaseValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class GroupProductsValidator implements BaseValidator, ConstraintValidator<HaveGroupProducts,Long> {

    private final GroupProductsService service;

    public GroupProductsValidator(GroupProductsService service) {
        this.service = service;
    }

    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return service.get(aLong).isSuccess();
    }
}
