package com.company.annotations.validators;

import com.company.annotations.NotNullBonusFieldForExpireType;
import com.company.models.view.bonus.CreateBonusViewModel;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class NotNullBonusFieldForExpireTypeValidator
        extends NotNullFieldsOfSelectBoxValidator
        implements ConstraintValidator<NotNullBonusFieldForExpireType, Object> {
    @Override
    public void initialize(NotNullBonusFieldForExpireType constraintAnnotation) {
        this.targetField = constraintAnnotation.fieldName();
        this.message = constraintAnnotation.message();
        this.mapFieldsWithValue = CreateBonusViewModel.getFieldsForExpire();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return isNotNullValid(o, constraintValidatorContext);
    }

}
