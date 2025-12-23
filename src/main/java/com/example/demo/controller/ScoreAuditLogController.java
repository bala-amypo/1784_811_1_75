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
        ScoreAuditLog log = service.findById(id); // returns null if not found
        if (log == null) {
            
            log = new ScoreAuditLog();
        }
        return log;
    }

   
    @GetMapping("/visitor/{visitorId}")
    public List<ScoreAuditLog> getLogsByVisitor(@PathVariable Long visitorId) {
        List<ScoreAuditLog> logs = service.findByVisitorId(visitorId);
        return logs;
    }
}
