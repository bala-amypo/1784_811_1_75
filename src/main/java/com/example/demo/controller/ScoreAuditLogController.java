package com.example.demo.controller;

import com.example.demo.model.ScoreAuditLog;
import com.example.demo.service.ScoreAuditLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/score-logs")
@Tag(name = "Score Audit Log")
public class ScoreAuditLogController {

    private final ScoreAuditLogService scoreAuditLogService;

    public ScoreAuditLogController(ScoreAuditLogService scoreAuditLogService) {
        this.scoreAuditLogService = scoreAuditLogService;
    }

    @PostMapping("/{visitorId}/{ruleId}")
    public ScoreAuditLog create(@PathVariable Long visitorId,
                                @PathVariable Long ruleId,
                                @RequestBody ScoreAuditLog log) {
        return scoreAuditLogService.logScoreChange(visitorId, ruleId, log);
    }

    @GetMapping("/{id}")
    public ScoreAuditLog get(@PathVariable Long id) {
        return scoreAuditLogService.getLog(id);
    }

    @GetMapping("/visitor/{visitorId}")
    public List<ScoreAuditLog> getByVisitor(@PathVariable Long visitorId) {
        return scoreAuditLogService.getLogsByVisitor(visitorId);
    }
}
