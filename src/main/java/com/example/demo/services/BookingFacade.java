package com.example.demo.services;

import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;

import java.time.LocalDateTime;

public interface BookingFacade {
    void bookEvent(User user, Event event, int seatNumber);
}
