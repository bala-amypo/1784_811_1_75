package com.example.demo.controller;

import com.example.demo.model.RiskScore;
import com.example.demo.service.RiskScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riskscores")
public class RiskScoreController {

    private final RiskScoreService riskScoreService;

    public RiskScoreController(RiskScoreService riskScoreService) {
        this.riskScoreService = riskScoreService;
    }

    @PostMapping
    public RiskScore create(@RequestBody RiskScore riskScore) {
        return riskScoreService.save(riskScore);
    }

    @GetMapping
    public List<RiskScore> getAll() {
        return riskScoreService.findAll();
    }

    @GetMapping("/{id}")
    public RiskScore getById(@PathVariable Long id) {
        return riskScoreService.findById(id);
    }
}
