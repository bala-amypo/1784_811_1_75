package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.RiskRule;
import com.example.demo.repository.RiskRuleRepository;
import com.example.demo.service.RiskRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskRuleServiceImpl implements RiskRuleService {

    private final RiskRuleRepository riskRuleRepository;

    public RiskRuleServiceImpl(RiskRuleRepository riskRuleRepository) {
        this.riskRuleRepository = riskRuleRepository;
    }

    @Override
    public RiskRule save(RiskRule rule) {
        return riskRuleRepository.save(rule);
    }

    @Override
    public List<RiskRule> findAll() {
        return riskRuleRepository.findAll();
    }

    @Override
    public RiskRule findById(Long id) {
        return riskRuleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("RiskRule not found with id " + id));
    }
}
