package ru.alexkrasnova.spring.lesson2.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ProductFilterValidator.class)
@Documented
public @interface ValidProductFilters {
    String message() default "{ProductFilters.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
