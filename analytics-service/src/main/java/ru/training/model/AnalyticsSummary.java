package ru.training.model;

import java.math.BigDecimal;
import java.util.Map;

public class AnalyticsSummary {
    private BigDecimal total;
    private Map<String, BigDecimal> categoryTotals;

    public AnalyticsSummary(BigDecimal total, Map<String, BigDecimal> categoryTotals) {
        this.total = total;
        this.categoryTotals = categoryTotals;
    }

    // геттеры
}

