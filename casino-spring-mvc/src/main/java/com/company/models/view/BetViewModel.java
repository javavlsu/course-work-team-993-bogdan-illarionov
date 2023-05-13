package com.company.models.view;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Validated
@Data
public class BetViewModel {
    private String login = SecurityContextHolder.getContext().getAuthentication().getName();
    private Long outcomeId;
    @Min(10)
    @Max(1000)
    private BigDecimal betSize;
}
