package com.example.demo.service;

import com.example.demo.model.Visitor;
import java.util.List;

public interface VisitorService {

    Visitor save(Visitor visitor);
    List<Visitor> findAll();
    Visitor update(Visitor visitor);
    void delete(Long id);
}
