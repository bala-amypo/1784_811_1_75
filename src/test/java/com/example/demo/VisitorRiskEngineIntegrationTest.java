package com.example.demo;

import com.example.demo.controller.*;
import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.service.*;
import com.example.demo.util.RiskLevelUtils;

import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@Test
public class VisitorRiskEngineIntegrationTest {

    @Mock private VisitorService visitorService;
    @Mock private VisitLogService visitLogService;
    @Mock private RiskRuleService riskRuleService;
    @Mock private RiskScoreService riskScoreService;
    @Mock private ScoreAuditLogService scoreAuditLogService;
    @Mock private UserService userService;

    private VisitorController visitorController;
    private VisitLogController visitLogController;
    private RiskRuleController riskRuleController;
    private RiskScoreController riskScoreController;
    private ScoreAuditLogController scoreAuditLogController;
    private AuthController authController;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.openMocks(this);

        visitorController = new VisitorController(visitorService);
        visitLogController = new VisitLogController(visitLogService);
        riskRuleController = new RiskRuleController(riskRuleService);
        riskScoreController = new RiskScoreController(riskScoreService);
        scoreAuditLogController = new ScoreAuditLogController(scoreAuditLogService);
        authController = new AuthController(userService);
    }

    // ========== VISITOR ==========
    @Test
    public void testCreateVisitor() {
        Visitor v = Visitor.builder().fullName("Alice").build();
        when(visitorService.createVisitor(any())).thenReturn(v);

        ResponseEntity<Visitor> resp = visitorController.create(v);
        Assert.assertEquals(resp.getBody().getFullName(), "Alice");
    }

    @Test
    public void testGetVisitor() {
        when(visitorService.getVisitor(1L))
                .thenReturn(Visitor.builder().id(1L).fullName("Bob").build());

        ResponseEntity<Visitor> resp = visitorController.get(1L);
        Assert.assertEquals(resp.getBody().getId().longValue(), 1L);
    }

    // ========== VISIT LOG ==========
    @Test
    public void testCreateVisitLog() {
        when(visitLogService.createVisitLog(eq(1L), any()))
                .thenReturn(VisitLog.builder().id(10L).build());

        ResponseEntity<VisitLog> resp = visitLogController.create(1L, new VisitLog());
        Assert.assertEquals(resp.getBody().getId().longValue(), 10L);
    }

    // ========== RISK RULE ==========
    @Test
    public void testCreateRiskRule() {
        RiskRule rule = RiskRule.builder().ruleName("AfterHours").build();
        when(riskRuleService.createRule(any())).thenReturn(rule);

        ResponseEntity<RiskRule> resp = riskRuleController.create(rule);
        Assert.assertEquals(resp.getBody().getRuleName(), "AfterHours");
    }

    // ========== RISK SCORE ==========
    @Test
    public void testEvaluateRisk() {
        when(riskScoreService.evaluateVisitor(1L))
                .thenReturn(RiskScore.builder().riskLevel("HIGH").totalScore(70).build());

        ResponseEntity<RiskScore> resp = riskScoreController.evaluate(1L);
        Assert.assertEquals(resp.getBody().getRiskLevel(), "HIGH");
    }

    // ========== AUTH ==========
    @Test
    public void testLogin() {
        when(userService.login(any()))
                .thenReturn(new AuthResponse("token123"));

        AuthRequest req = new AuthRequest("a@b.com", "pass");
        ResponseEntity<?> resp = authController.login(req);

        Assert.assertEquals(((AuthResponse) resp.getBody()).getToken(), "token123");
    }

    // ========== UTILS ==========
    @Test
    public void testRiskLevelUtils() {
        Assert.assertEquals(RiskLevelUtils.determineRiskLevel(10), "LOW");
        Assert.assertEquals(RiskLevelUtils.determineRiskLevel(40), "MEDIUM");
        Assert.assertEquals(RiskLevelUtils.determineRiskLevel(70), "HIGH");
        Assert.assertEquals(RiskLevelUtils.determineRiskLevel(90), "CRITICAL");
    }
}
