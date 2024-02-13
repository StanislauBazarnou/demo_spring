package com.example.demo;

import com.example.demo.services.BookingFacadeImpl;
import com.example.demo.services.EventServiceImpl;
import com.example.demo.services.TicketServiceImpl;
import com.example.demo.services.UserServiceImpl;
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
    private EventServiceImpl eventServiceImpl;

    @Mock
    private TicketServiceImpl ticketServiceImpl;

    @Mock
    private UserServiceImpl userServiceImpl;

    @InjectMocks
    private BookingFacadeImpl bookingFacade;

    @BeforeEach
    void setUp() {
        eventServiceImpl = Mockito.mock(EventServiceImpl.class);
        ticketServiceImpl = Mockito.mock(TicketServiceImpl.class);
        userServiceImpl = Mockito.mock(UserServiceImpl.class);

        bookingFacade = new BookingFacadeImpl(userServiceImpl, eventServiceImpl, ticketServiceImpl);
    }

}
