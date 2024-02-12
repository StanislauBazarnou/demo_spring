package com.example.demo.dao;

import com.example.demo.Storage;
import com.example.demo.model.Event;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class EventDao {
    private final Map<Long, Event> eventMap;

    @Autowired
    public EventDao(Storage database) {
        this.eventMap = database.getEventMap();
    }

    public Event save(Event event) {
        eventMap.put(event.getEventId(), event);
        return event;
    }

    public Event getById(long id) {
        return eventMap.get(id);
    }
}
