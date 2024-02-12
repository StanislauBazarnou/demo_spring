package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Ticket {
    private long ticketId;
    private long userId;
    private long eventId;
    private LocalDateTime eventDateTime;
    private int seatNumber;
    private double price;

    public Ticket() {
    }

    public Ticket(long id, long userId, long eventId, LocalDateTime eventDateTime, int seatNumber, double price) {
        this.ticketId = id;
        this.userId = userId;
        this.eventId = eventId;
        this.eventDateTime = eventDateTime;
        this.seatNumber = seatNumber;
        this.price = price;
    }
}
