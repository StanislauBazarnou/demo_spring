package com.example.demo.services;

import com.example.demo.dao.TicketDao;
import com.example.demo.model.Ticket;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class TicketServiceImpl implements TicketService {

    private final TicketDao ticketDao;

    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    public Ticket bookTicket(int seatNumber, LocalDateTime eventDateTime, long userId, long eventId) {
        Ticket ticket = new Ticket();

        ticket.setSeatNumber(seatNumber);
        ticket.setEventDateTime(eventDateTime);
        ticket.setUserId(userId);
        ticket.setEventId(eventId);
        log.info("Ticked is booked: {}", ticket);
        return ticketDao.putTicket(ticket.getTicketId(), ticket);
    }

    public Ticket getTicket(long ticketId) {
        return ticketDao.getTicketById(ticketId);
    }
}
