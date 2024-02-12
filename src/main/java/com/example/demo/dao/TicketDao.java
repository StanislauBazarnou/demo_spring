package com.example.demo.dao;

import com.example.demo.Storage;
import com.example.demo.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class TicketDao {
    private final Map<Long, Ticket> ticketMap;

    @Autowired
    public TicketDao(Storage database) {
        this.ticketMap = database.getTicketMap();
    }

    public Ticket save(Ticket ticket) {
        ticketMap.put(ticket.getUserId(), ticket);
        return ticket;
    }

    public Ticket getById(long id) {
        return ticketMap.get(id);
    }
}
