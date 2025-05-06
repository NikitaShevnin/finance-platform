package ru.training.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.training.model.AnalyticsSummary;
import ru.training.model.TransactionCreatedEvent;
import ru.training.repository.AnalyticsRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final AnalyticsRepository repository;

    public void processTransaction(TransactionCreatedEvent event) {
        repository.save(event);
    }

    public AnalyticsSummary getAnalyticsForUser(UUID userId) {
        List<TransactionCreatedEvent> transactions = repository.findByUserId(userId);
        BigDecimal total = transactions.stream()
                .map(TransactionCreatedEvent::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, BigDecimal> byCategory = transactions.stream()
                .collect(Collectors.groupingBy(
                        TransactionCreatedEvent::getCategory,
                        Collectors.reducing(BigDecimal.ZERO, TransactionCreatedEvent::getAmount, BigDecimal::add)
                ));

        return new AnalyticsSummary(total, byCategory);
    }
}

