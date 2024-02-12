package com.example.demo.services;

import com.example.demo.dao.EventDao;
import com.example.demo.model.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Slf4j
public class EventService {

    private EventDao eventDao;

    /**
     * An example of setter-based injection. You can use setter-based injection for the services as
     * they have single dependencies
     */
    @Autowired
    public void setEventDao(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public Event createEvent(String eventName, LocalDateTime date) {
        Event event = new Event();
        event.setEventName(eventName);
        event.setEventDate(date);
        log.info("Event is created: {}", event);
        return event;
    }

    public Event getEvent(long eventId) {
        return eventDao.getById(eventId);
    }
}
