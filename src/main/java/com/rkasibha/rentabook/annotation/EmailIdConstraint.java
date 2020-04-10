package com.rkasibha.rentabook.annotation;

import com.rkasibha.rentabook.validator.EmailIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailIdValidator.class)
public @interface EmailIdConstraint {
    String message() default "Invalid email id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
