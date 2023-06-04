package com.company.storage.models.bonus;

import com.company.storage.models.StorageOutcome;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "users_bonuses")
@AllArgsConstructor
@NoArgsConstructor
public class StorageUserBonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "bonus_id")
    private Long bonusId;

    @OneToMany(mappedBy = "mapId", fetch = FetchType.EAGER)
    private Set<StorageUserBonusConfig> config;
}
