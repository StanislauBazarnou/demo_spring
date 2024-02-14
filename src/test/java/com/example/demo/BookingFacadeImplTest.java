package com.example.demo;

import com.example.demo.model.Event;
import com.example.demo.model.User;
import com.example.demo.services.BookingFacadeImpl;
import com.example.demo.services.EventService;
import com.example.demo.services.TicketService;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingFacadeImplTest {

    @Mock
    private UserService userService;

    @Mock
    private EventService eventService;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private BookingFacadeImpl bookingFacadeImpl;


    @Test
    void testBookEvent() {
        // given
        User user = new User();
        user.setUserId(1);

        Event event = new Event();
        event.setEventId(1);
        event.setEventDateTime(LocalDateTime.now());

        int seatNumber = 23;

        when(userService.getUser(user.getUserId())).thenReturn(user);
        when(eventService.createEvent(event.getEventName(), event.getEventDateTime())).thenReturn(event);

        // when
        bookingFacadeImpl.bookEvent(user, event, seatNumber);

        // then
        verify(userService, times(1)).getUser(user.getUserId());
        verify(eventService, times(1)).createEvent(event.getEventName(), event.getEventDateTime());
        verify(ticketService, times(1))
                .bookTicket(seatNumber, event.getEventDateTime(), user.getUserId(), event.getEventId());
    }
}
