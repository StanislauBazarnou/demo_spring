package com.example.demo.services;

import com.example.demo.model.Event;
import com.example.demo.model.User;

import java.time.LocalDateTime;

public class BookingFacadeImpl implements BookingFacade {

    private final UserServiceImpl userServiceImpl;
    private final EventServiceImpl eventServiceImpl;
    private final TicketServiceImpl ticketServiceImpl;


    public BookingFacadeImpl(UserServiceImpl userServiceImpl, EventServiceImpl eventServiceImpl, TicketServiceImpl ticketServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.eventServiceImpl = eventServiceImpl;
        this.ticketServiceImpl = ticketServiceImpl;
    }

    @Override
    public void bookEvent(User user, Event event, int seatNumber) {
        userServiceImpl.getUser(user.getUserId());
        eventServiceImpl.createEvent(event.getEventName(), event.getEventDate());
        long userId = user.getUserId();
        long eventId = event.getEventId();
        LocalDateTime eventDateTime = event.getEventDate();
        ticketServiceImpl.bookTicket(seatNumber, eventDateTime, userId, eventId);
    }
}
