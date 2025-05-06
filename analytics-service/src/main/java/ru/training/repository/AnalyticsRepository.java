package ru.training.repository;

import org.springframework.stereotype.Repository;
import ru.training.model.TransactionCreatedEvent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AnalyticsRepository {

    private final Map<UUID, List<TransactionCreatedEvent>> storage = new ConcurrentHashMap<>();

    public void save(TransactionCreatedEvent event) {
        storage.computeIfAbsent(event.getUserId(), k -> new ArrayList<>()).add(event);
    }

    public List<TransactionCreatedEvent> findByUserId(UUID userId) {
        return storage.getOrDefault(userId, Collections.emptyList());
    }
}
