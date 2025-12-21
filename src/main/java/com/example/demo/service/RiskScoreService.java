package com.example.demo.service;

import com.example.demo.model.RiskScore;
import java.util.List;

public interface RiskScoreService {

    RiskScore save(RiskScore riskScore);

    List<RiskScore> findAll();

    RiskScore findById(Long id);
}
