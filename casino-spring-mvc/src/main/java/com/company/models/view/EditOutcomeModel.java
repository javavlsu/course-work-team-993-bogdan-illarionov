package com.company.models.view;

import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

@Data
public class EditOutcomeModel {
    private Long outcomeId;
    private String value;
    @Min(1)
    private Double koef;
}
