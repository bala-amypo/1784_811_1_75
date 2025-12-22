package com.example.demo.service;

import com.example.demo.model.ScoreAuditLog;
import java.util.List;

public interface ScoreAuditLogService {

    ScoreAuditLog save(ScoreAuditLog log);

    List<ScoreAuditLog> findAll();

    ScoreAuditLog findById(Long id);

    List<ScoreAuditLog> findByVisitorId(Long visitorId); // Add this method
}
