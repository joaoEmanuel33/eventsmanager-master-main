package com.senai.eventsmanager.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = TemNumerosValidador.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface HolandaFinlandes {

    String message() default "A senha deve conter o nome do país Holanda em finlandês";

    Class<?>[] groups () default {};
    Class<? extends Payload>[] payloud() default {};

}
