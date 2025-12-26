package com.example.demo.controller;

import com.example.demo.model.VisitLog;
import com.example.demo.service.VisitLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visit-logs")
@RequiredArgsConstructor
public class VisitLogController {

    private final VisitLogService visitLogService;

    @PostMapping("/{visitorId}")
    public ResponseEntity<VisitLog> create(
            @PathVariable Long visitorId,
            @RequestBody VisitLog log
    ) {
        return ResponseEntity.ok(
                visitLogService.createVisitLog(visitorId, log)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitLog> get(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(visitLogService.getLog(id));
    }

    @GetMapping("/visitor/{visitorId}")
    public ResponseEntity<List<VisitLog>> getByVisitor(
            @PathVariable Long visitorId
    ) {
        return ResponseEntity.ok(
                visitLogService.getLogsByVisitor(visitorId)
        );
    }
}
