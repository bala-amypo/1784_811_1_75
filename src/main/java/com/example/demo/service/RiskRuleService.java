package com.example.demo.service;

import com.example.demo.model.RiskRule;
import java.util.List;

public interface RiskRuleService {

    RiskRule save(RiskRule riskRule);

    List<RiskRule> findAll();

    RiskRule findById(Long id);
}
