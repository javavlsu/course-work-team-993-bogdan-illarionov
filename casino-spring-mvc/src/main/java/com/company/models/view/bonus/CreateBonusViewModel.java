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
            put(StorageBonus.COUNT_EXPIRE_TYPE_ID,
                    new ArrayList<>() {{
                        add("triggerCount");
            }});
            put(StorageBonus.TERM_EXPIRE_TYPE_ID,
                    new ArrayList<>() {{
                        add("toTerm");
                    }});
            put(StorageBonus.UNLIMITED_EXPIRE_TYPE_ID,
                    Collections.<String> emptyList());
        }};
    }

    public static Map<Short, List<String>> getFieldsForAction() {
        return new HashMap<>(){{
            put(StorageBonus.BALANCE_ADD_ACTION_ID,
                    new ArrayList<>() {{
                        add("bonusKoef");
                    }});
            put(StorageBonus.LOT_WIN_ACTION_ID,
                    new ArrayList<>() {{
                        add("lotsIds");
                        add("bonusKoef");
                    }});
            put(StorageBonus.LOT_PLAY_ACTION_ID,
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

        newBonus.setConfig(config);

        return newBonus;
    }
}
