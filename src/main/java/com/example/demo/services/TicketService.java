package com.example.demo.services;

import com.example.demo.dao.TicketDao;
import com.example.demo.model.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Slf4j
public class TicketService {

    @Autowired
    private TicketDao ticketDao;

    public Ticket bookTicket(int seatNumber, LocalDateTime eventDateTime, long userId, long eventId) {
        Ticket ticket = new Ticket();

        ticket.setSeatNumber(seatNumber);
        ticket.setEventDateTime(eventDateTime);
        ticket.setUserId(userId);
        ticket.setEventId(eventId);
        log.info("Ticked is booked: {}", ticket);
        return ticketDao.save(ticket);
    }

    public void cancelTicket(long ticketId) {
    }

    public Ticket getTicket(long ticketId) {
        return ticketDao.getById(ticketId);
    }
}
