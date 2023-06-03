package com.company.models.view;

import com.company.models.account.User;
import com.company.storage.models.StorageUser;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;

@Data
public class BetViewModel {
    private String login = SecurityContextHolder.getContext().getAuthentication().getName();
    private Long outcomeId;

    @Digits(integer = 6, fraction = 2)
    @DecimalMin(value = "10.0", message = "Should be greater than 10.")
    @DecimalMax(value = "10000.0", message = "Should be less than 10000.")
    private BigDecimal betSize;
}
