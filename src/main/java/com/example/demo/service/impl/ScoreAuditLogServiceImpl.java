package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ScoreAuditLog;
import com.example.demo.repository.ScoreAuditLogRepository;
import com.example.demo.service.ScoreAuditLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreAuditLogServiceImpl implements ScoreAuditLogService {

    private final ScoreAuditLogRepository repository;

    public ScoreAuditLogServiceImpl(ScoreAuditLogRepository repository) {
        this.repository = repository;
    }

    @Override
    public ScoreAuditLog save(ScoreAuditLog log) {
        return repository.save(log);
    }

    @Override
    public List<ScoreAuditLog> findAll() {
        return repository.findAll();
    }

    @Override
    public ScoreAuditLog findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ScoreAuditLog not found with id: " + id));
    }

    @Override
    public List<ScoreAuditLog> findByVisitorId(Long visitorId) {
        List<ScoreAuditLog> logs = repository.findByVisitorId(visitorId);
        if (logs.isEmpty()) {
            throw new ResourceNotFoundException("No ScoreAuditLogs found for visitor id: " + visitorId);
        }
        return logs;
    }
}
