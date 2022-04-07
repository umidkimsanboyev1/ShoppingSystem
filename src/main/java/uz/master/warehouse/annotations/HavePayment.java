package uz.master.warehouse.annotations;

import uz.master.warehouse.validator.payment.PaymentValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target(value = {FIELD, ANNOTATION_TYPE, CONSTRUCTOR, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PaymentValidator.class)
public @interface HavePayment {

    String message() default "Payment is not Valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
