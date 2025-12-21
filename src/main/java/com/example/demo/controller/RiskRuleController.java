package com.example.demo.controller;

import com.example.demo.model.RiskRule;
import com.example.demo.service.RiskRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riskrules")
public class RiskRuleController {

    private final RiskRuleService riskRuleService;

    public RiskRuleController(RiskRuleService riskRuleService) {
        this.riskRuleService = riskRuleService;
    }

    @PostMapping
    public RiskRule create(@RequestBody RiskRule riskRule) {
        return riskRuleService.save(riskRule);
    }

    @GetMapping
    public List<RiskRule> getAll() {
        return riskRuleService.findAll();
    }

    @GetMapping("/{id}")
    public RiskRule getById(@PathVariable Long id) {
        return riskRuleService.findById(id);
    }
}
