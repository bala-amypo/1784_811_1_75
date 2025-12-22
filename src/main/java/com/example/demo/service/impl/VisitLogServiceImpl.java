package com.example.demo.service.impl;

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
        return visitLogRepository.findById(id).orElse(null);
    }
}                   this is one of my impl should i cahange this 