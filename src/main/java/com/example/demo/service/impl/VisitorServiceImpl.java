package com.example.demo.service.impl;

import com.example.demo.model.Visitor;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.service.VisitorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;

    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public Visitor saveVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }

    public Visitor getVisitorById(Long id) {
        return visitorRepository.findById(id).orElse(null);
    }

    public void deleteVisitor(Long id) {
        visitorRepository.deleteById(id);
    }
}
