package com.example.demo;

import com.example.demo.dao.TicketDao;
import com.example.demo.model.Ticket;
import com.example.demo.model.TicketKey;
import com.example.demo.services.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketDao ticketDao;

    @InjectMocks
    private TicketServiceImpl ticketServiceImpl;

    @Test
    void testBookTicket() {
        // given
        Ticket ticket = Ticket.builder()
                .userId(1)
                .eventId(1)
                .eventDateTime(LocalDateTime.of(2024, 10, 10, 10, 10))
                .seatNumber(23)
                .price(49)
                .build();

        TicketKey ticketKey = new TicketKey(ticket.getTicketId()); // Create TicketKey

        // Set up ArgumentCaptor
        ArgumentCaptor<TicketKey> ticketKeyArgumentCaptor = ArgumentCaptor.forClass(TicketKey.class);
        ArgumentCaptor<Ticket> ticketArgumentCaptor = ArgumentCaptor.forClass(Ticket.class);

        // when
        when(ticketDao.putTicket(ticketKeyArgumentCaptor.capture(), ticketArgumentCaptor.capture())).thenReturn(ticket);
        Ticket bookedTicket = ticketServiceImpl.bookTicket(23, LocalDateTime.of(2024, 10, 10, 10, 10), 1, 1);

        // assert that arguments passed to ticketDao.putTicket have expected properties
        assertEquals(ticketKeyArgumentCaptor.getValue().id(), ticket.getTicketId());
        assertEquals(ticketArgumentCaptor.getValue().getUserId(), ticket.getUserId());

        // then assert that the bookedTicket returned is the same object as ticket
        assertEquals(ticket, bookedTicket);
    }
}
