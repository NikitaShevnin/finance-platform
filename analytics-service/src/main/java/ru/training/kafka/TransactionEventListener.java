package ru.training.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.training.model.TransactionCreatedEvent;
import ru.training.service.AnalyticsService;

@Service
@RequiredArgsConstructor
public class TransactionEventListener {

    private final AnalyticsService analyticsService;

    @KafkaListener(topics = "TransactionCreated", groupId = "analytics-group")
    public void handleTransactionCreated(TransactionCreatedEvent event) {
        analyticsService.processTransaction(event);
    }
}

