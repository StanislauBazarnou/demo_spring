package com.example.demo.services;

import com.example.demo.model.Ticket;

import java.time.LocalDateTime;

public interface TicketService {
    Ticket bookTicket(int seatNumber, LocalDateTime eventDateTime, long userId, long eventId);
    Ticket getTicket(long ticketId);
}
