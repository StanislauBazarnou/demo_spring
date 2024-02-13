package com.example.demo;

import com.example.demo.dao.TicketDao;
import com.example.demo.model.Ticket;
import com.example.demo.services.TicketServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
                .userId(1)
                .eventId(1)
                .eventDateTime(LocalDateTime.of(2024, 10, 10, 10, 10))
                .seatNumber(23)
                .price(49)
                .build();

        // when
        when(ticketDao.save(any(Ticket.class))).thenReturn(ticket); // when a ticket is saved, return it
        Ticket bookedTicket = ticketServiceImpl.bookTicket(23, LocalDateTime.of(2024, 10, 10, 10, 10), 1, 1);

        // then assert that the bookedTicket returned is the same object as ticket
        assertEquals(ticket, bookedTicket);
        // then verify that ticketDao.save() was actually called with any Ticket object argument
        verify(ticketDao, times(1)).save(any(Ticket.class));
    }

}
