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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "expire_type_id")
    private Short ExpireTypeId;

    @Column(name = "trigger_action_id")
    private Short TriggerActionId;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "bonus_id")
    private StorageBonusConfig config;
}
