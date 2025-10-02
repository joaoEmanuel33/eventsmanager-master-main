package com.senai.eventsmanager.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TemNumerosValidador implements ConstraintValidator<DeveTerNumeros, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.isEmpty()) return false; 
        boolean hasNumber = value.matches(".*\\d.*");

       boolean containsYashin = value.toLowerCase().contains("");
        
        return hasNumber && containsYashin;
    }

}
