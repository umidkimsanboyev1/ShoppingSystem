package uz.master.warehouse.annotations;


import uz.master.warehouse.validator.organization.CompanyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

@Target(value = {FIELD, ANNOTATION_TYPE,  CONSTRUCTOR, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CompanyValidator.class)
public @interface HaveCompany {

    String message() default "Company is not Valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
