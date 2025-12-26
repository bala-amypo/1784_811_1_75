package com.example.demo.service;

import com.example.demo.model.VisitLog;
import java.util.List;

public interface VisitLogService {

    List<VisitLog> findByVisitorId(Long visitorId);

}
