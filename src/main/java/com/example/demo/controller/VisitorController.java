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

   
    @PostMapping("/save")
    public Visitor saveVisitor(@RequestBody Visitor visitor) {
        return visitorService.saveVisitor(visitor); 
    }

  
    @GetMapping("/all")
    public List<Visitor> getAllVisitors() {
        return visitorService.getAllVisitors(); 
    }

  
    @GetMapping("/{id}")
    public Visitor getVisitorById(@PathVariable Long id) {
        return visitorService.getVisitorById(id); 
    }

    
    @DeleteMapping("/{id}")
    public void deleteVisitor(@PathVariable Long id) {
        visitorService.deleteVisitor(id); 
    }
}
