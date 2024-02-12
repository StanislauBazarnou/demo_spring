package com.example.demo;

import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;

import java.time.LocalDateTime;

public interface BookingFacade {
    User createUser(String username, String password);
    User getUser(long userId);
    Event createEvent(String eventName, LocalDateTime eventDateTime);
    Event getEvent(long eventId);
    Ticket bookTicket(long userId, long eventId, int seatNumber, LocalDateTime eventDateTime);
    Ticket getTicket(long ticketId);
    void cancelTicket(long ticketId);
}
