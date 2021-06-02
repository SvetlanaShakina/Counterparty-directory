package com.example.counterpartydirectory.validation.validator;


import com.example.counterpartydirectory.validation.BicValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BicValidator implements ConstraintValidator<BicValid, String> {
    @Override
    public boolean isValid(String bic, ConstraintValidatorContext context) {
        if (bic == null) return false;
        if (!bic.isEmpty()) {
            int numberCreditOrg = Integer.parseInt(bic.substring(bic.length() - 3));
            return numberCreditOrg > 50 && numberCreditOrg < 999;
        }
        return false;
    }
}
