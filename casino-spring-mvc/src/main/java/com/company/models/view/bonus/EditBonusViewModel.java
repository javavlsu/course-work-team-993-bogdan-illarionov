package com.company.models.view.bonus;

import com.company.annotations.NotNullBonusFieldForExpireType;
import com.company.annotations.NotNullBonusFieldForTriggerAction;
import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageBonusConfig;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.FieldError;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NotNullBonusFieldForTriggerAction(fieldName = "triggerActionTypeId")
@NotNullBonusFieldForExpireType(fieldName = "expireTypeId")
public class EditBonusViewModel {

    private Long id;

    @NotBlank(message = "Shouldn't be blank.")
    @Length(min = 4, max = 49, message = "Not match with length constraint.")
    private String name;

    private Short expireTypeId;

    private Short triggerActionTypeId;

    private Boolean isEnabled;

    @Min(value = 1, message = "Should be greater than 1.")
    @Max(value = 1000, message = "Should be less than 1000.")
    @Nullable
    private Integer triggerCount;

    @Nullable
    private String toTerm;

    @Nullable
    private String lotsIds;

    @Digits(integer = 3, fraction = 2)
    @DecimalMin(value = "1.0", message = "Should be greater than 1.")
    @DecimalMax(value = "1000.0", message = "Should be less than 10000.")
    @Nullable
    private BigDecimal bonusKoef;

    public static EditBonusViewModel FromModel(StorageBonus bonus) {
        var viewModel = new EditBonusViewModel();

        viewModel.setId(bonus.getId());
        viewModel.setName(bonus.getName());
        viewModel.setIsEnabled(bonus.getIsEnabled());
        viewModel.setExpireTypeId(bonus.getExpireTypeId());
        viewModel.setTriggerActionTypeId(bonus.getTriggerActionId());

        viewModel.setBonusKoef(bonus.getConfig().getBonusKoef());
        viewModel.setLotsIds(bonus.getConfig().getLotsId());;
        viewModel.setTriggerCount(bonus.getConfig().getTriggerCount());

        if (bonus.getConfig().getToTerm() != null) {
            var str = "";

            var seconds = bonus.getConfig().getToTerm().getSeconds();

            var minutes = seconds / 60;
            var hours = minutes / 60;
            minutes = minutes % 60;
            var days = hours / 24;
            hours = hours % 24;

            if (days != 0) {
                str += "days:" + days;
            }

            if (hours != 0) {
                if (!str.equals("")) {
                    str += ";";
                }

                str += "hours:" + hours;
            }

            if (minutes != 0) {
                if (!str.equals("")) {
                    str += ";";
                }

                str += "minutes:" + minutes;
            }

            viewModel.setToTerm(str);
        }

        return viewModel;
    }

    public static StorageBonus ToModel(EditBonusViewModel viewModel) {
        var newBonus = new StorageBonus();
        newBonus.setId(viewModel.getId());
        newBonus.setName(viewModel.getName());
        newBonus.setIsEnabled(viewModel.getIsEnabled());
        newBonus.setExpireTypeId(viewModel.getExpireTypeId());
        newBonus.setTriggerActionId(viewModel.getTriggerActionTypeId());

        var config = new StorageBonusConfig();

        if (viewModel.getExpireTypeId() == StorageBonus.COUNT_EXPIRE_TYPE_ID) {
            config.setTriggerCount(viewModel.getTriggerCount());
        }
        else if (viewModel.getExpireTypeId() == StorageBonus.TERM_EXPIRE_TYPE_ID) {

            var duration = Duration.ZERO;

            if (viewModel.getToTerm() != null) {
                var splited = viewModel.getToTerm().split(";");

                var ref = new Object() {
                    public void setNewDuration(Duration newDuration) {
                        this.newDuration = newDuration;
                    }

                    public Duration getNewDuration() {
                        return newDuration;
                    }

                    private Duration newDuration;
                };

                ref.setNewDuration(duration);

                Arrays.stream(splited).forEach(x -> {
                    var split = x.split(":");

                    switch (split[0]) {
                        case "hours" -> ref.setNewDuration(ref.getNewDuration().plusHours(Integer.parseInt(split[1])));
                        case "minutes" -> ref.setNewDuration(ref.getNewDuration().plusMinutes(Integer.parseInt(split[1])));
                        case "days" -> ref.setNewDuration(ref.getNewDuration().plusDays(Integer.parseInt(split[1])));
                    }
                });

                duration = ref.getNewDuration();


            }
            config.setToTerm(duration);
        }

        if (viewModel.getTriggerActionTypeId() == StorageBonus.BALANCE_ADD_ACTION_ID) {
            config.setBonusKoef(viewModel.getBonusKoef());
        }
        else if (viewModel.getTriggerActionTypeId() == StorageBonus.LOT_WIN_ACTION_ID) {
            config.setBonusKoef(viewModel.getBonusKoef());
            config.setLotsId(viewModel.getLotsIds());
        }
        else if (viewModel.getTriggerActionTypeId() == StorageBonus.LOT_PLAY_ACTION_ID) {
            config.setLotsId(viewModel.getLotsIds());
        }

        config.setBonusId(newBonus.getId());

        newBonus.setConfig(config);

        return newBonus;
    }

    public String getStringExpireType() {
        return switch (expireTypeId) {
            case StorageBonus.COUNT_EXPIRE_TYPE_ID -> "Count";
            case StorageBonus.TERM_EXPIRE_TYPE_ID -> "Term";
            case StorageBonus.UNLIMITED_EXPIRE_TYPE_ID -> "Unlimited";
            default -> "None";
        };
    }

    public String getStringTriggerType() {
        return switch (triggerActionTypeId) {
            case StorageBonus.BALANCE_ADD_ACTION_ID -> "Balance Add";
            case StorageBonus.LOT_WIN_ACTION_ID -> "Lot win";
            case StorageBonus.LOT_PLAY_ACTION_ID -> "Lot play";
            default -> "None";
        };
    }
}
