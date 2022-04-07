package uz.master.warehouse.annotations;

import uz.master.warehouse.validator.organization.OrganizationValidator;
import uz.master.warehouse.validator.product.ProductValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ProductValidator.class)
public @interface HaveProduct {

    String message() default "Product is not Valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
