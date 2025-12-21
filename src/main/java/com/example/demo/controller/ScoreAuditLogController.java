package com.example.demo.controller;

import com.example.demo.model.ScoreAuditLog;
import com.example.demo.service.ScoreAuditLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/score-logs")
public class ScoreAuditLogController {

    private final ScoreAuditLogService service;

    public ScoreAuditLogController(ScoreAuditLogService service) {
        this.service = service;
    }

    // POST /api/score-logs
    @PostMapping
    public ScoreAuditLog create(@RequestBody ScoreAuditLog log) {
        return service.save(log);
    }

    // GET /api/score-logs/visitor/{visitorId}
    @GetMapping("/visitor/{visitorId}")
    public List<ScoreAuditLog> getByVisitor(@PathVariable Long visitorId) {
        return service.findByVisitorId(visitorId);
    }

    // GET /api/score-logs/{id}
    @GetMapping("/{id}")
    public ScoreAuditLog getById(@PathVariable Long id) {
        return service.findById(id).orElse(null);
    }
}
