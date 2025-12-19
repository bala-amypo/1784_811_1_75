package com.example.demo.controller;

import com.example.demo.model.VisitLog;
import com.example.demo.service.VisitLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visit-logs")
@Tag(name = "Visit Log")
public class VisitLogController {

    private final VisitLogService visitLogService;

    public VisitLogController(VisitLogService visitLogService) {
        this.visitLogService = visitLogService;
    }

    @PostMapping("/{visitorId}")
    public VisitLog create(@PathVariable Long visitorId,
                           @RequestBody VisitLog log) {
        return visitLogService.createVisitLog(visitorId, log);
    }

    @GetMapping("/{id}")
    public VisitLog get(@PathVariable Long id) {
        return visitLogService.getLog(id);
    }

    @GetMapping("/visitor/{visitorId}")
    public List<VisitLog> getByVisitor(@PathVariable Long visitorId) {
        return visitLogService.getLogsByVisitor(visitorId);
    }
}
