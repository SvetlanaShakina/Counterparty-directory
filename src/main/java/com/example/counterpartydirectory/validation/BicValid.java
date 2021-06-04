package com.example.counterpartydirectory.validation;

import com.example.counterpartydirectory.validation.validator.BicValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Валидация БИК
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BicValidator.class)
@Documented
public @interface BicValid {
    String message() default "Некорректный БИК";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
