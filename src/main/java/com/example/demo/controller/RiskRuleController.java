package com.example.demo.controller;

import com.example.demo.model.RiskRule;
import com.example.demo.service.RiskRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/risk-rules")
@RequiredArgsConstructor
public class RiskRuleController {

    private final RiskRuleService riskRuleService;

    @PostMapping
    public ResponseEntity<RiskRule> create(
            @RequestBody RiskRule rule
    ) {
        return ResponseEntity.ok(riskRuleService.createRule(rule));
    }

    @GetMapping
    public ResponseEntity<List<RiskRule>> getAll() {
        return ResponseEntity.ok(riskRuleService.getAllRules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiskRule> get(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(riskRuleService.getRule(id));
    }

    
}
