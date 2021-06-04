package com.example.counterpartydirectory.validation;

import com.example.counterpartydirectory.validation.validator.AccountNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Валидация номера счета
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountNumberValidator.class)
@Documented
public @interface AccountNumberValid {
    String message() default "Некорректный номер счета";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String bic();

    String accountNumber();

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        AccountNumberValid[] value();
    }
}