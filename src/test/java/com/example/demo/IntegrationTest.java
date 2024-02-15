package com.example.demo;

import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.services.BookingFacade;
import com.example.demo.services.EventService;
import com.example.demo.services.TicketService;
import com.example.demo.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IntegrationTest {

    @Autowired
    BookingFacade bookingFacade;
    @Autowired
    UserService userService;
    @Autowired
    TicketService ticketService;
    @Autowired
    EventService eventService;

    @Test
    void bookingFacadeTest() {
        // given
        User user = new User();
        user.setUserId(1);
        Event event = new Event();
        event.setEventId(1);
        event.setEventDateTime(LocalDateTime.now());
        int seatNumber = 23;
        // when
        bookingFacade.bookEvent(user, event, seatNumber);
        // then
        assertNotNull(userService.getUser(user.getUserId()));
        assertNotNull(eventService.getEventById(event.getEventId()));
    }

    @Test
    void testFindUserByName() {
        // given
        long userId = 3;
        String expectedUserName = "Alice Smith";
        // when
        User user = userService.getUser(userId);
        // then
        assertEquals(expectedUserName, user.getUserName(), "User name should be the same as the one used in Users.json");
    }

    @Test
    void testFindTicketById() {
        // given
        long ticketId = 2;
        int expectedSeatNumber = 12;
        // when
        Ticket ticket = ticketService.getTicket(ticketId);
        // then
        assertEquals(expectedSeatNumber, ticket.getSeatNumber());
    }

    @Test
    void testFindEventByTime() {
        // given
        String expectedEventName = "Football match";
        LocalDateTime eventDateTime = LocalDateTime.parse("2022-07-10T16:00:00");
        // when
        Event event = eventService.getEventByDate(eventDateTime);
        // then
        assertEquals(expectedEventName, event.getEventName());
    }

}

