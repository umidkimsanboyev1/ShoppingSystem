package uz.master.warehouse.annotations;

import uz.master.warehouse.validator.organization.MarketValidator;

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
@Constraint(validatedBy = MarketValidator.class)
public @interface HaveMarket {

    String message() default "Market is not Valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
