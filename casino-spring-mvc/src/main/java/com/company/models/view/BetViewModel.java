package com.company.models.view;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class BetViewModel {
    private Long userId;
    private Long outcomeId;
    private Double betSize;
}
