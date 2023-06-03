package com.company.models.view;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBalanceViewModel {

    public static final String REGEX_ACCOUNT_NUMBER = "^[0-9]{20}";

    @NotEmpty(message = "Number shouldn't be empty.")
    @Pattern(regexp = REGEX_ACCOUNT_NUMBER, message = "Number not match with format.")
    private String account;

    @Digits(integer = 6, fraction = 2)
    @DecimalMin(value = "1.0", message = "Should be greater than 1.")
    @DecimalMax(value = "1000000.0", message = "Should be less than 1000000.")
    private BigDecimal positiveBalanceDelta = BigDecimal.ZERO;
}
