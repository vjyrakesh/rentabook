package com.rkasibha.rentabook.annotation;


import com.rkasibha.rentabook.validator.CityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CityValidator.class)
public @interface CityConstraint {
    String message() default "Invalid city";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
