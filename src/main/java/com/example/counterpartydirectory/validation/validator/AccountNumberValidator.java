package com.example.counterpartydirectory.validation.validator;

import com.example.counterpartydirectory.entity.Counterparty;
import com.example.counterpartydirectory.validation.AccountNumberValid;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountNumberValidator implements ConstraintValidator<AccountNumberValid, Counterparty> {
    private static final int[] WEIGHT_COEFFICIENTS = new int[]{7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1, 3, 7, 1};
    private String bic;
    private String accountNumber;
    private String message;

    @Override
    public void initialize(AccountNumberValid constraintAnnotation) {
        bic = constraintAnnotation.bic();
        accountNumber = constraintAnnotation.accountNumber();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Counterparty value, ConstraintValidatorContext context) {
        if (value == null) return false;
        boolean isValidAccount = false;
        try {
            String bicValue = BeanUtils.getProperty(value, bic);
            String accountNumberValue = BeanUtils.getProperty(value, accountNumber);
            if (bicValue == null || accountNumberValue == null) return false;
            if (!bicValue.isEmpty() && !accountNumberValue.isEmpty()) {
                if (Character.getNumericValue(bicValue.charAt(6)) == 0 && Character.getNumericValue(bicValue.charAt(7)) == 0) {
                    isValidAccount = checkAccountRKC(bicValue, accountNumberValue);
                } else {
                    isValidAccount = checkAccountCredit(bicValue, accountNumberValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!isValidAccount) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(accountNumber).addConstraintViolation();
        }
        return isValidAccount;
    }

    private boolean checkAccountRKC(String bic, String accountNumber) {
        String toCheck = "0" + bic.substring(4, 6) + accountNumber;
        return calculateControlSum(toCheck) % 10 == 0;
    }

    private boolean checkAccountCredit(String bic, String accountNumber) {
        String toCheck = bic.substring(bic.length() - 3) + accountNumber;
        return calculateControlSum(toCheck) % 10 == 0;
    }

    private int calculateControlSum(String toCheck) {
        int controlSum = 0;
        for (int i = 0; i < toCheck.length(); i++) {
            controlSum += Character.getNumericValue(toCheck.charAt(i)) * WEIGHT_COEFFICIENTS[i];
        }
        return controlSum;
    }
}