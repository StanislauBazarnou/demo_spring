package com.example.demo.dao;

import com.example.demo.Storage;
import com.example.demo.model.Event;
import com.example.demo.model.EventKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Slf4j
public class EventDao {
    private final Map<EventKey, Event> eventMap;

    @Autowired
    public EventDao(Storage database) {
        this.eventMap = database.getEventMap();
    }

    public void save(Event event) {
        EventKey eventKey = new EventKey(event.getEventId());
        eventMap.put(eventKey, event);
    }

    public Event getById(long id) {
        EventKey eventKey = new EventKey(id);
        return eventMap.get(eventKey);
    }
}
