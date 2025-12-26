package com.example.demo;

import com.example.demo.controller.*;
import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.*;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.*;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.util.RiskLevelUtils;

import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@Test
@Listeners(TestResultListener.class)
public class VisitorRiskEngineIntegrationTest {

    // ================= MOCKS =================
    @Mock private UserRepository userRepository;
    @Mock private VisitorService visitorService;
    @Mock private VisitLogService visitLogService;
    @Mock private RiskRuleService riskRuleService;
    @Mock private RiskScoreService riskScoreService;
    @Mock private ScoreAuditLogService scoreAuditLogService;
    @Mock private JwtTokenProvider jwtTokenProvider;

    private AuthController authController;
    private VisitorController visitorController;
    private VisitLogController visitLogController;
    private RiskRuleController riskRuleController;
    private RiskScoreController riskScoreController;
    private ScoreAuditLogController scoreAuditLogController;

    // ================= SETUP =================
    @BeforeClass
    public void setup() {
        MockitoAnnotations.openMocks(this);

        UserService userService = new UserServiceImpl(
                userRepository,
                mock(org.springframework.security.crypto.password.PasswordEncoder.class),
                jwtTokenProvider
        );

        authController = new AuthController(userService);
        visitorController = new VisitorController(visitorService);
        visitLogController = new VisitLogController(visitLogService);
        riskRuleController = new RiskRuleController(riskRuleService);
        riskScoreController = new RiskScoreController(riskScoreService);
        scoreAuditLogController = new ScoreAuditLogController(scoreAuditLogService);
    }

    // ================= BASIC =================
    @Test(priority = 1)
    public void testControllersCreated() {
        Assert.assertNotNull(visitorController);
        Assert.assertNotNull(authController);
    }

    // ================= VISITOR =================
    @Test(priority = 2)
    public void testCreateVisitor() {
        Visitor v = Visitor.builder().fullName("Alice").phone("999").idProof("ID1").build();
        when(visitorService.createVisitor(any())).thenReturn(v);

        ResponseEntity<Visitor> resp = visitorController.create(v);
        Assert.assertEquals(resp.getBody().getFullName(), "Alice");
    }

    @Test(priority = 3)
    public void testGetVisitor() {
        when(visitorService.getVisitor(1L))
                .thenReturn(Visitor.builder().id(1L).fullName("Alice").build());

        ResponseEntity<Visitor> resp = visitorController.get(1L);
        Assert.assertEquals(resp.getBody().getId().longValue(), 1L);
    }

    // ================= VISIT LOG =================
    @Test(priority = 4)
    public void testCreateVisitLog() {
        when(visitLogService.createVisitLog(eq(1L), any()))
                .thenReturn(VisitLog.builder().id(10L).build());

        ResponseEntity<VisitLog> resp = visitLogController.create(1L, new VisitLog());
        Assert.assertEquals(resp.getBody().getId().longValue(), 10L);
    }

    // ================= RISK RULE =================
    @Test(priority = 5)
    public void testCreateRiskRule() {
        RiskRule rule = RiskRule.builder().ruleName("AfterHours").build();
        when(riskRuleService.createRule(any())).thenReturn(rule);

        ResponseEntity<RiskRule> resp = riskRuleController.create(rule);
        Assert.assertEquals(resp.getBody().getRuleName(), "AfterHours");
    }

    // ================= RISK SCORE =================
    @Test(priority = 6)
    public void testEvaluateRiskScore() {
        when(riskScoreService.evaluateVisitor(1L))
                .thenReturn(RiskScore.builder().totalScore(30).riskLevel("MEDIUM").build());

        ResponseEntity<RiskScore> resp = riskScoreController.evaluate(1L);
        Assert.assertEquals(resp.getBody().getRiskLevel(), "MEDIUM");
    }

    // ================= AUTH =================
    @Test(priority = 7)
    public void testRegisterUser() {
        RegisterRequest req = new RegisterRequest();
        req.setEmail("a@b.com");
        req.setPassword("pass");
        req.setRoles(Set.of("USER"));

        when(userRepository.findByEmail("a@b.com")).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        ResponseEntity<?> resp = authController.register(req);
        Assert.assertEquals(((User) resp.getBody()).getEmail(), "a@b.com");
    }

    @Test(priority = 8)
    public void testLoginUser() {
        AuthRequest req = new AuthRequest("a@b.com", "pass");

        UserService mockUserService = mock(UserService.class);
        when(mockUserService.login(any())).thenReturn(new AuthResponse("token"));

        AuthController ac = new AuthController(mockUserService);
        ResponseEntity<?> resp = ac.login(req);

        Assert.assertEquals(((AuthResponse) resp.getBody()).getToken(), "token");
    }

    // ================= UTILS =================
    @Test(priority = 9)
    public void testRiskLevelUtils() {
        Assert.assertEquals(RiskLevelUtils.determineRiskLevel(0), "LOW");
        Assert.assertEquals(RiskLevelUtils.determineRiskLevel(25), "MEDIUM");
        Assert.assertEquals(RiskLevelUtils.determineRiskLevel(55), "HIGH");
        Assert.assertEquals(RiskLevelUtils.determineRiskLevel(85), "CRITICAL");
    }

    // ================= FINAL =================
    @Test(priority = 10)
    public void finalSmokeTest() {
        Assert.assertNotNull(riskRuleController);
        Assert.assertNotNull(scoreAuditLogController);
    }
}
