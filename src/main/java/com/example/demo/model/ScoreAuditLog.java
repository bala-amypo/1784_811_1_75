package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreAuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many audit logs can belong to one visitor
    @ManyToOne
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    // Many audit logs can refer to one risk rule
    @ManyToOne
    @JoinColumn(name = "risk_rule_id")
    private RiskRule appliedRule;

    private Integer scoreChange;

    private String reason;

    private LocalDateTime loggedAt;

   
    @PrePersist
    public void onCreate() {
        this.loggedAt = LocalDateTime.now();
    }
}
