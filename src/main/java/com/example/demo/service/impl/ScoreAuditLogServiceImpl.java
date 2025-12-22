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
                .orElseThrow(() -> new ResourceNotFoundException("ScoreAuditLog with id " + id + " not found"));
    }

    @Override
    public List<ScoreAuditLog> findByVisitorId(Long visitorId) {
        List<ScoreAuditLog> logs = repository.findByVisitorId(visitorId);
        if (logs.isEmpty()) {
            throw new ResourceNotFoundException("No audit logs found for visitorId " + visitorId);
        }
        return logs;
    }

    @Override
    public void delete(Long id) {
        ScoreAuditLog log = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ScoreAuditLog with id " + id + " not found"));
        repository.delete(log);
    }
}
