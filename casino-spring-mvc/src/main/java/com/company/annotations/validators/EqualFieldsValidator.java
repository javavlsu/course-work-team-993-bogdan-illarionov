package com.company.annotations.validators;

import com.company.annotations.EqualFields;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EqualFieldsValidator implements ConstraintValidator<EqualFields, Object> {

    private String baseField;
    private String matchField;
    private String message;

    @Override
    public void initialize(EqualFields constraint) {
        baseField = constraint.field();
        matchField = constraint.matchField();
        message = constraint.message();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {

        var isValid = false;

        try {
            var baseFieldValue = getFieldValue(object, baseField);
            var matchFieldValue = getFieldValue(object, matchField);
            isValid =  baseFieldValue != null && baseFieldValue.equals(matchFieldValue);
        } catch (Exception e) {
            isValid =  false;
        }

        if (!isValid)
        {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(matchField)
                    .addConstraintViolation();
        }

        return isValid;
    }

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        var passwordField = clazz.getDeclaredField(fieldName);
        passwordField.setAccessible(true);
        return passwordField.get(object);
    }

}
