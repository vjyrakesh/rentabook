package com.rkasibha.rentabook.validator;

import com.rkasibha.rentabook.annotation.EmailIdConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailIdValidator implements ConstraintValidator<EmailIdConstraint, String> {

    @Override
    public void initialize(EmailIdConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches(".+@.+\\.[a-z]{2,4}");
    }
}
