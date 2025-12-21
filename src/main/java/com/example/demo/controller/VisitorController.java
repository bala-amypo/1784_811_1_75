package com.example.demo.controller;

import com.example.demo.model.Visitor;
import com.example.demo.service.VisitorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {

    private final VisitorService service;

    public VisitorController(VisitorService service) {
        this.service = service;
    }

    // POST /api/visitors
    @PostMapping
    public Visitor createVisitor(@RequestBody Visitor visitor) {
        return service.save(visitor);
    }

    // GET /api/visitors
    @GetMapping
    public List<Visitor> getAllVisitors() {
        return service.findAll();
    }

    // GET /api/visitors/{id}
    @GetMapping("/{id}")
    public Visitor getVisitorById(@PathVariable Long id) {
        return service.findAll()
                .stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
