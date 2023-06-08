package com.company.annotations;

import com.company.annotations.validators.NotNullBonusFieldForTriggerActionValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NotNullBonusFieldForTriggerActionValidator.class})
public @interface NotNullBonusFieldForTriggerAction {

    String message() default "Should be filled.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();
}