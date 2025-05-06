package ru.training.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.training.model.AnalyticsSummary;
import ru.training.service.AnalyticsService;

import java.util.UUID;

// controller/AnalyticsController.java
@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/{userId}")
    public AnalyticsSummary getAnalytics(@PathVariable UUID userId) {
        return analyticsService.getAnalyticsForUser(userId);
    }
}

