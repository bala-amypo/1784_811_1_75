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
    public RiskRule save(RiskRule riskRule) {
        return riskRuleRepository.save(riskRule);
    }

    @Override
    public List<RiskRule> findAll() {
        return riskRuleRepository.findAll();
    }

    @Override
    public RiskRule findById(Long id) {
        return riskRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RiskRule with id " + id + " not found"));
    }

    @Override
    public RiskRule update(Long id, RiskRule riskRuleDetails) {
        RiskRule existingRule = riskRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RiskRule with id " + id + " not found"));

        // Update fields
        existingRule.setName(riskRuleDetails.getName());
        existingRule.setDescription(riskRuleDetails.getDescription());
        existingRule.setScore(riskRuleDetails.getScore());

        return riskRuleRepository.save(existingRule);
    }

    @Override
    public void delete(Long id) {
        RiskRule existingRule = riskRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RiskRule with id " + id + " not found"));
        riskRuleRepository.delete(existingRule);
    }
}
