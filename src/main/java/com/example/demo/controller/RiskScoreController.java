package com.example.demo.controller;

import com.example.demo.model.RiskScore;
import com.example.demo.service.RiskScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/risk-scores")
@RequiredArgsConstructor
public class RiskScoreController {

    private final RiskScoreService riskScoreService;

    @PostMapping("/evaluate/{visitorId}")
    public ResponseEntity<RiskScore> evaluate(
            @PathVariable Long visitorId
    ) {
        return ResponseEntity.ok(
                riskScoreService.evaluateVisitor(visitorId)
        );
    }

    @GetMapping("/visitor/{visitorId}")
    public ResponseEntity<RiskScore> getByVisitor(
            @PathVariable Long visitorId
    ) {
        return ResponseEntity.ok(
                riskScoreService.getScoreForVisitor(visitorId)
        );
    }

    @GetMapping
    public ResponseEntity<List<RiskScore>> getAll() {
        return ResponseEntity.ok(riskScoreService.getAllScores());
    }

    @GetMapping("/{id}")
public ResponseEntity<RiskScore> get(@PathVariable Long id) {
    return ResponseEntity.ok(riskScoreService.findById(id));
}

}
