package com.senai.eventsmanager.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TemHolanda implements ConstraintValidator<HolandaFinlandes, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null || value.isEmpty()) return false; 
        boolean hasNumber = value.matches(".*\\d.*");

        boolean containsHolanda = value.toLowerCase().contains("alankomaat"); 

        boolean containsFinlandFlag = value.contains("🇫🇮");
        boolean containsNorwayFlag = value.contains("🇳🇴");
        boolean containsSwedenFlag = value.contains("🇸🇪");

        // Retorna true apenas se todas as condições forem atendidas
        return hasNumber && containsHolanda && containsFinlandFlag && containsNorwayFlag && containsSwedenFlag;
    }

}
