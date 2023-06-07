package com.company.models.view.bonus;

import com.company.annotations.NotNullBonusFieldForExpireType;
import com.company.annotations.NotNullBonusFieldForTriggerAction;
import com.company.models.enums.BonusExpireType;
import com.company.models.enums.BonusTriggerAction;
import com.company.storage.models.bonus.StorageBonus;
import com.company.storage.models.bonus.StorageBonusConfig;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NotNullBonusFieldForTriggerAction(fieldName = "triggerActionTypeId")
@NotNullBonusFieldForExpireType(fieldName = "expireTypeId")
public class CreateBonusViewModel {

    public static Map<Short, List<String>> getFieldsForExpire() {
        return new HashMap<>(){{
            put(BonusExpireType.Count.getValue(),
                    new ArrayList<>() {{
                        add("triggerCount");
            }});
            put(BonusExpireType.Term.getValue(),
                    new ArrayList<>() {{
                        add("toTerm");
                    }});
            put(BonusExpireType.Unlimited.getValue(),
                    Collections.<String> emptyList());
        }};
    }

    public static Map<Short, List<String>> getFieldsForAction() {
        return new HashMap<>(){{
            put(BonusTriggerAction.BonusAdd.getValue(),
                    new ArrayList<>() {{
                        add("bonusKoef");
                    }});
            put(BonusTriggerAction.LotWin.getValue(),
                    new ArrayList<>() {{
                        add("lotsIds");
                        add("bonusKoef");
                    }});
            put(BonusTriggerAction.LotPlay.getValue(),
                    new ArrayList<>() {{
                        add("lotsIds");
                    }});
        }};
    }

    @NotBlank(message = "Shouldn't be blank.")
    @Length(min = 4, max = 49, message = "Not match with length constraint.")
    private String name;

    @Min(value = 1, message = "Need to chose")
    private Short expireTypeId;

    @Min(value = 1, message = "Need to chose")
    private Short triggerActionTypeId;

    private Boolean isEnabled;

    @Min(value = 1, message = "Should be greater than 1.")
    @Max(value = 1000, message = "Should be less than 1000.")
    @Nullable
    private Integer triggerCount;

    // todo regex check
    @Nullable
    private String toTerm;

    @Nullable
    // todo regex check
    private String lotsIds;

    @Digits(integer = 3, fraction = 2)
    @DecimalMin(value = "1.0", message = "Should be greater than 1.")
    @DecimalMax(value = "1000.0", message = "Should be less than 10000.")
    @Nullable
    private BigDecimal bonusKoef;


    public static StorageBonus ToModel(CreateBonusViewModel viewModel) {
        var newBonus = new StorageBonus();
        newBonus.setName(viewModel.getName());
        newBonus.setIsEnabled(viewModel.getIsEnabled());
        newBonus.setExpireTypeId(viewModel.getExpireTypeId());
        newBonus.setTriggerActionId(viewModel.getTriggerActionTypeId());

        var config = new StorageBonusConfig();

        if (viewModel.getExpireTypeId() == BonusExpireType.Count.getValue()) {
            config.setTriggerCount(viewModel.getTriggerCount());
        }
        else if (viewModel.getExpireTypeId() == BonusExpireType.Term.getValue()) {

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

                    switch (split[1]) {
                        case "hours" -> ref.setNewDuration(ref.getNewDuration().plusHours(Integer.parseInt(split[0])));
                        case "minutes" -> ref.setNewDuration(ref.getNewDuration().plusMinutes(Integer.parseInt(split[0])));
                        case "days" -> ref.setNewDuration(ref.getNewDuration().plusDays(Integer.parseInt(split[0])));
                    }
                });

                duration = ref.getNewDuration();


            }
            config.setToTerm(duration);
        }

        if (viewModel.getTriggerActionTypeId() == BonusTriggerAction.BonusAdd.getValue()) {
            config.setBonusKoef(viewModel.getBonusKoef());
        }
        else if (viewModel.getTriggerActionTypeId() == BonusTriggerAction.LotWin.getValue()) {
            config.setBonusKoef(viewModel.getBonusKoef());
            config.setLotsId(viewModel.getLotsIds());
        }
        else if (viewModel.getTriggerActionTypeId() == BonusTriggerAction.LotPlay.getValue()) {
            config.setLotsId(viewModel.getLotsIds());
        }

        newBonus.setConfig(config);

        return newBonus;
    }
}
