package com.rkasibha.rentabook.validator;

import com.rkasibha.rentabook.annotation.CountryConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryValidator implements ConstraintValidator<CountryConstraint, String> {

    private final List<String> countries = Arrays.asList("India", "USA", "China", "Germany", "UK");

    @Override
    public void initialize(CountryConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        return s.matches("^[a-zA-Z]{2,15}$") && countries.contains(s);
    }
}
