package com.example.demo;

import com.example.demo.services.EventService;
import com.example.demo.services.TicketService;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookingFacadeImplTest {
    @Mock
    private EventService eventService;

    @Mock
    private TicketService ticketService;

    @Mock
    private UserService userService;

    @InjectMocks
    private BookingFacadeImpl bookingFacade;

    @BeforeEach
    void setUp() {
        eventService = Mockito.mock(EventService.class);
        ticketService = Mockito.mock(TicketService.class);
        userService = Mockito.mock(UserService.class);

        bookingFacade = new BookingFacadeImpl(userService, eventService, ticketService);
    }

    @Test
    void testGetEventById() {
        bookingFacade.getEventById(1);
        Mockito.verify(eventService, Mockito.times(1)).getEvent(1);
    }

    @Test
    void testGetUserById() {
        bookingFacade.getUserById(1);
        Mockito.verify(userService, Mockito.times(1)).getUser(1);
    }

    @Test
    void testGetTicketById() {
        bookingFacade.getTicketById(1);
        Mockito.verify(ticketService, Mockito.times(1)).getTicket(1);
    }
}
