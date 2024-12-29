package csci.ooad.polymorphia.observer;

import csci.ooad.polymorphia.EventType;
import csci.ooad.polymorphia.IObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class TestObserver implements IObserver {

    private static final Logger logger = LoggerFactory.getLogger(AudibleObserver.class);

    private static final Map<EventType, List<String>> eventsByType = new HashMap<>();

    @Override
    public void update(EventType event, String eventDescription) {
        // This could store general events if needed
        addEvent(event, eventDescription);
    }

    public void addEvent(EventType eventType, String description) {
        eventsByType.putIfAbsent(eventType, new ArrayList<>());
        eventsByType.get(eventType).add(description);
    }

    public List<String> getEventsByType(EventType eventType) {
        return eventsByType.getOrDefault(eventType, new ArrayList<>());
    }

    public int getEventCount(EventType eventType) {
        return getEventsByType(eventType).size();
    }

    public boolean hasEvent(EventType eventType, String description) {
        return getEventsByType(eventType).contains(description);
    }

    public List<Entry<EventType, List<String>>> getEvents() {
        return eventsByType.entrySet().stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public void printEvents() {
        for (Map.Entry<EventType, List<String>> entry : eventsByType.entrySet()) {
            EventType eventType = entry.getKey();
            List<String> events = entry.getValue();

            logger.info("Events for " + eventType + ":");
            for (String event : events) {
                logger.info("  - " + event);
            }
        }
    }

    public static void clear() {
        eventsByType.clear();
    }
}
