package com.example.demo.util;

public class RiskLevelUtils {

    private RiskLevelUtils() {
        // Utility class – prevent object creation
    }

    public static String determineRiskLevel(int score) {

        if (score >= 80) {
            return "CRITICAL";
        }

        if (score >= 50) {
            return "HIGH";
        }

        if (score >= 20) {
            return "MEDIUM";
        }

        return "LOW";
    }
}
