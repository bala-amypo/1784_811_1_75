package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ScoreAuditLog;
import com.example.demo.repository.ScoreAuditLogRepository;
import com.example.demo.service.ScoreAuditLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreAuditLogServiceImpl implements ScoreAuditLogService {

    private final ScoreAuditLogRepository scoreAuditLogRepository;

    public ScoreAuditLogServiceImpl(ScoreAuditLogRepository scoreAuditLogRepository) {
        this.scoreAuditLogRepository = scoreAuditLogRepository;
    }

    @Override
    public ScoreAuditLog logScoreChange(Long visitorId, Long ruleId, ScoreAuditLog log) {
        return scoreAuditLogRepository.save(log);
    }

    @Override
    public ScoreAuditLog getLog(Long id) {
        return scoreAuditLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Audit log not found"));
    }

    @Override
    public List<ScoreAuditLog> getLogsByVisitor(Long visitorId) {
        return scoreAuditLogRepository.findAll();
    }
}
