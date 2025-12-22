package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
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

    @Override
    public Visitor save(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    @Override
    public List<Visitor> findAll() {
        return visitorRepository.findAll();
    }

    @Override
    public Visitor findById(Long id) {
        return visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor with id " + id + " not found"));
    }

    @Override
    public Visitor update(Long id, Visitor visitorDetails) {
        Visitor existingVisitor = visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor with id " + id + " not found"));

        // Update fields
        existingVisitor.setName(visitorDetails.getName());
        existingVisitor.setEmail(visitorDetails.getEmail());
        existingVisitor.setPhone(visitorDetails.getPhone());

        return visitorRepository.save(existingVisitor);
    }

    @Override
    public void delete(Long id) {
        Visitor existingVisitor = visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor with id " + id + " not found"));
        visitorRepository.delete(existingVisitor);
    }
}
