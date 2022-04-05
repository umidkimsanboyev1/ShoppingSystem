package uz.master.warehouse.validator.product;

import org.springframework.stereotype.Component;
import uz.master.warehouse.annotations.HaveProduct;
import uz.master.warehouse.services.product.ProductService;
import uz.master.warehouse.validator.BaseValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ProductValidator implements ConstraintValidator<HaveProduct, Long>, BaseValidator {

    private final ProductService service;

    public ProductValidator(ProductService service) {
        this.service = service;
    }


    @Override
    public boolean isValid(Long aLong, ConstraintValidatorContext constraintValidatorContext) {
        return !service.get(aLong).isSuccess();
    }
}
