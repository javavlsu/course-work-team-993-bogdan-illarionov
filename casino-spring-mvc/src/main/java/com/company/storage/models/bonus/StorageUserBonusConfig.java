package com.company.storage.models.bonus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user_bonuses_config")
@AllArgsConstructor
@NoArgsConstructor
public class StorageUserBonusConfig {

    public static String IS_ENABLED_PARAM_NAME = "is_enabled";
    public static String COUNT_PARAM_NAME = "trigger_count";
    public static String TERM_PARAM_NAME = "to_term";

    @Column(name = "map_id")
    @Id
    private Long mapId;

    @Column(name = "param_name")
    @Id
    private String name;

    @Column(name = "param_value")
    private String value;

    @Column(name = "param_type")
    private String type;
}
