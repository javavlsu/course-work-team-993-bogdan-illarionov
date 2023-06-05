package com.company.storage.models.bonus;

import com.company.storage.models.StorageOutcome;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Entity
@Table(name = "bonuses")
@AllArgsConstructor
@NoArgsConstructor
public class StorageBonus {

    public static final short BALANCE_ADD_ACTION_ID = 1;
    public static final short LOT_WIN_ACTION_ID = 2;
    public static final int LOT_PLAY_ACTION_ID = 3;

    public static final short COUNT_EXPIRE_TYPE_ID = 1;
    public static final short TERM_EXPIRE_TYPE_ID = 2;
    public static final short UNLIMITED_EXPIRE_TYPE_ID = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @Column(name = "Name")
    private String name;

    @Column(name = "is_enabled")
    private Boolean isEnabled = false;

    @Column(name = "expire_type_id")
    private Short ExpireTypeId;

    @Column(name = "trigger_action_id")
    private Short TriggerActionId;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "bonus_id")
    private StorageBonusConfig config;
}
