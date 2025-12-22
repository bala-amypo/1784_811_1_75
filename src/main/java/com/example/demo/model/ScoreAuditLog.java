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

  
    @ManyToOne
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    
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
