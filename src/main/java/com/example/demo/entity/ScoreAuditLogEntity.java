package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreAuditLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "visitor_id")
    private VisitorEntity visitor;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    private RiskRuleEntity appliedRule;

    private Integer scoreChange;
    private String reason;
    private LocalDateTime loggedAt;

    @PrePersist
    public void onCreate() {
        this.loggedAt = LocalDateTime.now();
    }
}
