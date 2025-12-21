package com.example.demo.service.impl;

import com.example.demo.model.RiskScore;
import com.example.demo.repository.RiskScoreRepository;
import com.example.demo.service.RiskScoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiskScoreServiceImpl implements RiskScoreService {

    private final RiskScoreRepository riskScoreRepository;

    public RiskScoreServiceImpl(RiskScoreRepository riskScoreRepository) {
        this.riskScoreRepository = riskScoreRepository;
    }

    @Override
    public RiskScore save(RiskScore riskScore) {
        return riskScoreRepository.save(riskScore);
    }

    @Override
    public List<RiskScore> findAll() {
        return riskScoreRepository.findAll();
    }

    @Override
    public RiskScore findById(Long id) {
        return riskScoreRepository.findById(id).orElse(null);
    }
}
