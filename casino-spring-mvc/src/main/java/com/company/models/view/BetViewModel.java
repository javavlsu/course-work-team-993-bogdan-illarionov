package com.company.models.view;

import com.company.models.account.User;
import com.company.storage.models.StorageUser;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;

@Data
public class BetViewModel {
    private String login = SecurityContextHolder.getContext().getAuthentication().getName();
    private Long outcomeId;
    @Min(10)
    private Double betSize;
}
