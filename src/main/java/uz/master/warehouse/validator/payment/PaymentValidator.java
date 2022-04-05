package uz.master.warehouse.validator.payment;

import org.springframework.stereotype.Component;
import uz.master.warehouse.annotations.HaveMarket;
import uz.master.warehouse.services.payment.PaymentService;
import uz.master.warehouse.validator.BaseValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PaymentValidator implements ConstraintValidator<HaveMarket, Long>, BaseValidator {
    private final PaymentService service;

    public PaymentValidator(PaymentService service) {
        this.service = service;
    }


    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return service.get(id).isSuccess();
    }
}
