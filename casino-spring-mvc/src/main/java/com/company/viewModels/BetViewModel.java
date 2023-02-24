package com.company.viewModels;

import jakarta.validation.constraints.Min;

public class BetViewModel {
    public long userId;
    public long lotId;
    public long outcomeId;

    @Min(1)
    public double price;
}
