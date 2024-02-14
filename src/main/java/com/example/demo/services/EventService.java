package com.example.demo.services;

import com.example.demo.model.Event;

import java.time.LocalDateTime;

public interface EventService {
    Event createEvent(String eventName, LocalDateTime date);
    Event getEventById(long eventId);
    Event getEventByDate(LocalDateTime eventDateTime);
}
