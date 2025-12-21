package com.example.demo.service;

import com.example.demo.model.ScoreAuditLog;

import java.util.List;
import java.util.Optional;

public interface ScoreAuditLogService {

    ScoreAuditLog save(ScoreAuditLog log);

    List<ScoreAuditLog> findAll();

    Optional<ScoreAuditLog> findById(Long id);

    List<ScoreAuditLog> findByVisitorId(Long visitorId);
}
