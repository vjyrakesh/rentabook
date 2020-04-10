package com.rkasibha.rentabook.annotation;

import com.rkasibha.rentabook.validator.ContactNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ContactNumberValidator.class)
public @interface ContactNumberConstraint {
    String message() default "Not a valid contact number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
