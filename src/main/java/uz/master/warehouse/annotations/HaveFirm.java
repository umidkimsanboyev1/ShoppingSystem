package uz.master.warehouse.annotations;

import uz.master.warehouse.validator.organization.CompanyValidator;
import uz.master.warehouse.validator.organization.FirmValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

@Target(value = {FIELD, ANNOTATION_TYPE,  CONSTRUCTOR, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = FirmValidator.class)
public @interface HaveFirm {
    String message() default "Firm is not Valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
