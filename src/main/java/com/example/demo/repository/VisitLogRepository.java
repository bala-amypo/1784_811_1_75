package com.example.demo.repository;

import com.example.demo.model.VisitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface VisitLogRepository extends JpaRepository<VisitLog, Long> {

    // ✅ REQUIRED for controller & tests
    List<VisitLog> findByVisitorId(Long visitorId);

    // Existing query (keep as is)
    @Query("SELECT v FROM VisitLog v WHERE v.visitor.id = :visitorId AND v.entryTime >= :since")
    List<VisitLog> findByVisitorSince(
            @Param("visitorId") Long visitorId,
            @Param("since") LocalDateTime since
    );

    // Existing query (keep as is)
    @Query("SELECT COUNT(v) FROM VisitLog v WHERE v.visitor.id = :visitorId " +
           "AND v.entryTime BETWEEN :start AND :end")
    long countVisitsInWindow(
            @Param("visitorId") Long visitorId,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
