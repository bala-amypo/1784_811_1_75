package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiskRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;

    /*
     * Allowed values (as per requirement file):
     * AFTER_HOURS
     * FREQUENT_VISITS
     * BLACKLIST
     * KEYWORD
     * CUSTOM
     */
    private String ruleType;

    private Integer threshold;
    private Integer scoreImpact;
    private LocalDateTime createdAt;
}
