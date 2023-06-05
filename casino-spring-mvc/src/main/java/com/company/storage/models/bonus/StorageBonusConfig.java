package com.company.storage.models.bonus;


import com.vladmihalcea.hibernate.type.interval.PostgreSQLIntervalType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;

@Data
@Entity
@Table(name = "bonus_config")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class StorageBonusConfig {

    @Id
    @Column(name = "bonus_id")
    private Long bonusId;

    @Column(name = "trigger_count")
    private Integer triggerCount;

    @Type(PostgreSQLIntervalType.class)
    @Column(name = "to_term")
    private Duration toTerm;

    @Column(name = "lots_ids")
    private String lotsId;

    @Column(name = "bonus_koef")
    private BigDecimal bonusKoef;
}
