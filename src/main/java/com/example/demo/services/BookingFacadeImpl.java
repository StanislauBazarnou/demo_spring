package com.example.demo.services;

import com.example.demo.model.Event;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class BookingFacadeImpl implements BookingFacade {

    private final UserService userService;
    private final EventService eventService;
    private final TicketService ticketService;

    public BookingFacadeImpl(UserService userService, EventService eventService, TicketService ticketService) {
        this.userService = userService;
        this.eventService = eventService;
        this.ticketService = ticketService;
    }

    @Override
    public void bookEvent(User user, Event event, int seatNumber) {
        User actualUser = userService.getUser(user.getUserId());
        Event actualEvent = eventService.createEvent(event.getEventName(), event.getEventDate());
        long userId = actualUser.getUserId();
        long eventId = actualEvent.getEventId();
        LocalDateTime eventDateTime = actualEvent.getEventDate();
        ticketService.bookTicket(seatNumber, eventDateTime, userId, eventId);
        log.info("Event is booked: {}", event);
    }
}
