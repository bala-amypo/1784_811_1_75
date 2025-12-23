package com.example.demo.controller;

import com.example.demo.model.ScoreAuditLog;
import com.example.demo.service.ScoreAuditLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/score-audit")
public class ScoreAuditLogController {

    private final ScoreAuditLogService service;

    public ScoreAuditLogController(ScoreAuditLogService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ScoreAuditLog saveLog(@RequestBody ScoreAuditLog log) {
        return service.save(log);
    }

    @GetMapping("/all")
    public List<ScoreAuditLog> getAllLogs() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ScoreAuditLog getLogById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/visitor/{visitorId}")
    public List<ScoreAuditLog> getLogsByVisitor(@PathVariable Long visitorId) {
        return service.findByVisitorId(visitorId);
    }
}
