package com.example.demo.service.impl;

import com.example.demo.model.ScoreAuditLog;
import com.example.demo.repository.ScoreAuditLogRepository;
import com.example.demo.service.ScoreAuditLogService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<ScoreAuditLog> findById(Long id) {
        return repository.findById(id);
    }

    // ✅ THIS METHOD WAS MISSING
    @Override
    public List<ScoreAuditLog> findByVisitorId(Long visitorId) {
        return repository.findByVisitorId(visitorId);
    }
}
