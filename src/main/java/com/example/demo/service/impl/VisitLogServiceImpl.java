package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.VisitLog;
import com.example.demo.repository.VisitLogRepository;
import com.example.demo.service.VisitLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitLogServiceImpl implements VisitLogService {

    private final VisitLogRepository visitLogRepository;

    public VisitLogServiceImpl(VisitLogRepository visitLogRepository) {
        this.visitLogRepository = visitLogRepository;
    }

    @Override
    public VisitLog save(VisitLog visitLog) {
        return visitLogRepository.save(visitLog);
    }

    @Override
    public List<VisitLog> findAll() {
        return visitLogRepository.findAll();
    }

    @Override
    public VisitLog findById(Long id) {
        return visitLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VisitLog with id " + id + " not found"));
    }

    @Override
    public VisitLog update(Long id, VisitLog visitLogDetails) {
        VisitLog existingLog = visitLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VisitLog with id " + id + " not found"));

        // Update fields as needed
        existingLog.setVisitorId(visitLogDetails.getVisitorId());
        existingLog.setEntryTime(visitLogDetails.getEntryTime());
        existingLog.setExitTime(visitLogDetails.getExitTime());

        return visitLogRepository.save(existingLog);
    }

    @Override
    public void delete(Long id) {
        VisitLog existingLog = visitLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("VisitLog with id " + id + " not found"));
        visitLogRepository.delete(existingLog);
    }
}
