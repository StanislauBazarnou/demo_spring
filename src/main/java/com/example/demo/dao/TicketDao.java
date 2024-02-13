package com.example.demo.dao;

import com.example.demo.Storage;
import com.example.demo.model.Ticket;

public class TicketDao {
    private final Storage storage;

    // one constructor - @Autowired is not required
    public TicketDao(Storage storage) {
        this.storage = storage;
    }

    public Ticket getTicketById(Long id) {
        return storage.getTicketMap().get(id);
    }

    public Ticket putTicket(Long id, Ticket ticket) {
        return storage.getTicketMap().put(id, ticket);
    }

    public Ticket removeTicket(Long id) {
        return storage.getTicketMap().remove(id);
    }

}
