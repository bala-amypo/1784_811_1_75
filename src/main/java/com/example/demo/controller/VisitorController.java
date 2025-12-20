package com.example.demo.controller;

import com.example.demo.model.Visitor;
import com.example.demo.service.VisitorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @PostMapping
    public Visitor create(@RequestBody Visitor visitor) {
        return visitorService.save(visitor);
    }

    @GetMapping
    public List<Visitor> getAll() {
        return visitorService.getAll();
    }

    @PutMapping("/{id}")
    public Visitor update(@PathVariable Long id, @RequestBody Visitor visitor) {
        visitor.setId(id);
        return visitorService.save(visitor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        visitorService.delete(id);
    }
}
