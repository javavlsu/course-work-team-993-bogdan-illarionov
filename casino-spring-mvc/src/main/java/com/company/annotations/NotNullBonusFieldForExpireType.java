package com.company.annotations;

import com.company.annotations.validators.NotNullBonusFieldForExpireTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NotNullBonusFieldForExpireTypeValidator.class})
public @interface NotNullBonusFieldForExpireType {

    String message() default "Should be filled.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldName();
}