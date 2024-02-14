package com.example.demo.dao;

import com.example.demo.Storage;
import com.example.demo.model.Event;
import com.example.demo.model.EventKey;
import com.example.demo.model.EventKeyByDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
public class EventDao {
    private final Map<EventKey, Event> eventMap;
    private final Map<EventKeyByDate, Event> eventDateMap;

    @Autowired
    public EventDao(Storage database) {
        this.eventMap = database.getEventMap();
        this.eventDateMap = database.getEventDateMap();
    }

    public void save(Event event) {
        EventKey eventKey = new EventKey(event.getEventId());
        eventMap.put(eventKey, event);
        log.info("Event saved to the storage: {}", event);
    }

    public Event getEventById(long id) {
        EventKey eventKey = new EventKey(id);
        return eventMap.get(eventKey);
    }

    public Event getEventByDate(LocalDateTime eventDateTime) {
        for(Map.Entry<EventKeyByDate, Event> entry : eventDateMap.entrySet()) {
            if(entry.getKey().eventDateTime().equals(eventDateTime)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
