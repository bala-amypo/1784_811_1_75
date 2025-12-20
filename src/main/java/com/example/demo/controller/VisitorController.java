package com.example.demo.controller;

import com.example.demo.model.Visitor;
import com.example.demo.service.VisitorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    private final VisitorService service;

    public VisitorController(VisitorService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Visitor create(@RequestBody Visitor visitor) {
        return service.save(visitor);
    }

    // READ
    @GetMapping
    public List<Visitor> getAll() {
        return service.findAll();
    }

    // UPDATE
    @PostMapping("/update")
    public Visitor update(@RequestBody Visitor visitor) {
        return service.update(visitor);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
