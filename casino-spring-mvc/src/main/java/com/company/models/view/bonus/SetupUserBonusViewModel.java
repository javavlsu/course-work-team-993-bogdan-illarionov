package com.company.models.view.bonus;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SetupUserBonusViewModel {
    @Min(value = 1, message = "Should be chosen.")
    private Long userId;

    @Min(value = 1, message = "Should be chosen.")
    private Long bonusId;
}
