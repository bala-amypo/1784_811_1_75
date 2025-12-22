
package com.example.demo.service;

import com.example.demo.model.Visitor;
import java.util.List;

public interface VisitorService {

    Visitor saveVisitor(Visitor visitor);

    List<Visitor> getAllVisitors();

    Visitor getVisitorById(Long id);

    void deleteVisitor(Long id);
}
