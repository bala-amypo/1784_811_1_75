package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiskScoreEntity {          

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "visitor_id")
    private VisitorEntity visitor;

    private Integer totalScore;
    private String riskLevel;
    private LocalDateTime evaluatedAt;

    @PrePersist
    public void onCreate() {
        this.evaluatedAt = LocalDateTime.now();
    }
}
