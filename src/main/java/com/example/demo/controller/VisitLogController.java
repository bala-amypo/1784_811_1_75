package com.example.demo.controller;

import com.example.demo.model.VisitLog;
import com.example.demo.service.VisitLogService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/visitlogs")
public class VisitLogController {

    private final VisitLogService visitLogService;

    public VisitLogController(VisitLogService visitLogService) {
        this.visitLogService = visitLogService;
    }

    @PostMapping
    public VisitLog create(@RequestBody VisitLog visitLog) {
        return visitLogService.save(visitLog);
    }

    @GetMapping
    public List<VisitLog> getAll() {
        return visitLogService.findAll();
    }

    @GetMapping("/{id}")
    public VisitLog getById(@PathVariable Long id) {
        return visitLogService.findById(id);
    }
}
