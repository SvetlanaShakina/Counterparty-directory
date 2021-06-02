package com.example.counterpartydirectory.validation.validator;

import com.example.counterpartydirectory.validation.InnValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InnValidator implements ConstraintValidator<InnValid, String> {
    private static final int[] WEIGHT_COEFFICIENTS = new int[]{2, 4, 10, 3, 5, 9, 4, 6, 8, 0};

    @Override
    public boolean isValid(String inn, ConstraintValidatorContext context) {
        if (inn == null || inn.length() != 10) return false;
        int controlSum = 0;
        for (int i = 0; i < inn.length(); i++) {
            controlSum += Character.getNumericValue(inn.charAt(i)) * WEIGHT_COEFFICIENTS[i];
        }
        int controlNumber = controlSum % 11;
        if (controlNumber > 9) {
            controlNumber = controlNumber % 10;
        }
        return controlNumber == Character.getNumericValue(inn.charAt(9));
    }
}