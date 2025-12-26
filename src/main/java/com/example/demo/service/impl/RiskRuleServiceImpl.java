package com.example.demo.service.impl;

import com.example.demo.model.RiskRule;
import com.example.demo.repository.RiskRuleRepository;
import com.example.demo.service.RiskRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiskRuleServiceImpl implements RiskRuleService {

    private final RiskRuleRepository riskRuleRepository;

    @Override
    public RiskRule createRule(RiskRule rule) {

        if (riskRuleRepository.findByRuleName(rule.getRuleName()).isPresent()) {
            throw new RuntimeException("Rule name must be unique");
        }

        if (rule.getThreshold() < 0 || rule.getScoreImpact() < 0) {
            throw new IllegalArgumentException("Invalid values");
        }

        return riskRuleRepository.save(rule);
    }

    @Override
    public List<RiskRule> getAllRules() {
        return riskRuleRepository.findAll();
    }

    @Override
    public RiskRule getRule(Long id) {
        return riskRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }
}
