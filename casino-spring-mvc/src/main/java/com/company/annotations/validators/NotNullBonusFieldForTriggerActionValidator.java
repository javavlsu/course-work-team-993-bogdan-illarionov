package com.company.annotations.validators;

import com.company.annotations.NotNullBonusFieldForTriggerAction;
import com.company.models.view.bonus.CreateBonusViewModel;
import com.company.storage.models.bonus.StorageBonus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotNullBonusFieldForTriggerActionValidator
        extends NotNullFieldsOfSelectBoxValidator
        implements ConstraintValidator<NotNullBonusFieldForTriggerAction, Object> {

    @Override
    public void initialize(NotNullBonusFieldForTriggerAction constraintAnnotation) {
        this.targetField = constraintAnnotation.fieldName();
        this.message = constraintAnnotation.message();
        this.mapFieldsWithValue = CreateBonusViewModel.getFieldsForAction();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        return isNotNullValid(o, constraintValidatorContext);
    }
}
