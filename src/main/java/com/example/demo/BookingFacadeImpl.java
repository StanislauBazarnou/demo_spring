package com.example.demo;

import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.services.EventService;
import com.example.demo.services.TicketService;
import com.example.demo.services.UserService;

import java.time.LocalDateTime;

public class BookingFacadeImpl implements BookingFacade {

    private final UserService userService;
    private final EventService eventService;
    private final TicketService ticketService;

    /*
     BookingFacadeImpl needs multiple services - it's a good candidate for constructor-based injection
     Marking a constructor with @Autowired isn't necessary when the target bean class only defines
     one constructor to begin with. As of Spring 4.3, it treats such a constructor as if it were
     annotated with @Autowired
    */

    public BookingFacadeImpl(UserService userService, EventService eventService, TicketService ticketService) {
        this.userService = userService;
        this.eventService = eventService;
        this.ticketService = ticketService;
    }

    @Override
    public User createUser(String username, String password) {
        return userService.createUser(username, password);
    }

    @Override
    public User getUserById(long userId) {
        return userService.getUser(userId);
    }

    @Override
    public Event createEvent(String eventName, LocalDateTime eventTime) {
        return eventService.createEvent(eventName, eventTime);
    }

    @Override
    public Event getEventById(long eventId) {
        return eventService.getEvent(eventId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int seatNumber, LocalDateTime eventDateTime) {
        return ticketService.bookTicket(seatNumber, eventDateTime, userId, eventId);
    }

    @Override
    public Ticket getTicketById(long ticketId) {
        return ticketService.getTicket(ticketId);
    }

    @Override
    public void cancelTicket(long ticketId) {
        ticketService.cancelTicket(ticketId);
    }
}
