package com.example.demo.dao;

import com.example.demo.Storage;
import com.example.demo.model.Ticket;
import com.example.demo.model.TicketKey;

public class TicketDao {
    private final Storage storage;

    public TicketDao(Storage storage) {
        this.storage = storage;
    }

    public Ticket getTicketById(TicketKey id) {
        return storage.getTicketMap().get(id);
    }

    public Ticket putTicket(TicketKey id, Ticket ticket) {
        return storage.getTicketMap().put(id, ticket);
    }

    public Ticket removeTicket(TicketKey id) {
        return storage.getTicketMap().remove(id);
    }

}
