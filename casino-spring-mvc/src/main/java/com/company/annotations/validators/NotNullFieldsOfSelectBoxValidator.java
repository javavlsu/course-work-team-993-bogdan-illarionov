package com.company.annotations.validators;

import com.company.annotations.NotNullBonusFieldForExpireType;
import com.company.storage.models.bonus.StorageBonus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class NotNullFieldsOfSelectBoxValidator {

    protected String targetField;

    protected String message;

    protected Map<Short, List<String>> mapFieldsWithValue;

    public boolean isNotNullValid(Object object, ConstraintValidatorContext context) {

        var isValid = true;

        var errors = new ArrayList<String>();

        try {
            var targetValue = (Short)getFieldValue(object, targetField);

            for (var fieldName : mapFieldsWithValue.get(targetValue)) {
                var fieldValue = getFieldValue(object, fieldName);

                if (Objects.isNull(fieldValue) ||
                        (fieldValue instanceof String && fieldValue.equals(""))) {
                    errors.add(fieldName);
                }
            }
        } catch (Exception e) {
            isValid =  false;
        }

        if (!isValid || errors.size() != 0)
        {
            context.disableDefaultConstraintViolation();

            if (!isValid) {
                context
                        .buildConstraintViolationWithTemplate("Fields validation error.")
                        .addPropertyNode(targetField)
                        .addConstraintViolation();

            }
            else {
                for (var error : errors) {
                    context
                            .buildConstraintViolationWithTemplate(message)
                            .addPropertyNode(error)
                            .addConstraintViolation();
                }
            }
        }

        return isValid && (errors.size() == 0);
    }

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        var field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);

        return field.get(object);
    }
}
