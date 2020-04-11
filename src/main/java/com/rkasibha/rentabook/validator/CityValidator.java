package com.rkasibha.rentabook.validator;

import com.rkasibha.rentabook.annotation.CityConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CityValidator implements ConstraintValidator<CityConstraint, String> {

    @Override
    public void initialize(CityConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches("^[a-zA-Z]{3,15}$");
    }
}
