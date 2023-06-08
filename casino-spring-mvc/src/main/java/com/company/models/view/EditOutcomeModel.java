package com.company.models.view;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;

@Data
public class EditOutcomeModel {
    private Long outcomeId;
    private String value;

    @Digits(integer = 3, fraction = 2)
    @DecimalMin(value = "1.0", message = "Should be greater than 1.")
    @DecimalMax(value = "1000.0", message = "Should be less than 10000.")
    private BigDecimal koef;
}
