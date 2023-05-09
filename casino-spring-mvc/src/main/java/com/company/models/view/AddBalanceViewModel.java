package com.company.models.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBalanceViewModel {

    private String account;

    private BigDecimal positiveBalanceDelta = BigDecimal.ZERO;
}
