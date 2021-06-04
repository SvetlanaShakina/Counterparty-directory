package com.example.counterpartydirectory.validation;

import com.example.counterpartydirectory.validation.validator.InnValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * Валидация ИНН
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InnValidator.class)
@Documented
public @interface InnValid {
    String message() default "Некорректный ИНН";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
