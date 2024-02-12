package com.example.demo;

import com.example.demo.model.Event;
import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Slf4j
@Component
@Getter
public class Storage {
    @Value("${path.to.users}")
    private String pathToUsers;
    @Value("${path.to.tickets}")
    private String pathToTickets;
    @Value("${path.to.events}")
    private String pathToEvents;

    private final Map<Long, User> userMap = new HashMap<>();
    private final Map<Long, Event> eventMap = new HashMap<>();
    private final Map<Long, Ticket> ticketMap = new HashMap<>();

    @PostConstruct
    public void initUserData() {
        // logic how we retrieve data from Jsons to Maps (to read data from file and initialize the maps)
        ObjectMapper mapper = new ObjectMapper();
        try {
            User[] users = mapper.readValue(new File(pathToUsers), User[].class);
            for (User user : users) {
                userMap.put(user.getUserId(), user);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error initializing data from file", e);
        }
        log.info("Init Users Data");
    }

    /**
     * Keep in mind though, writing to a file after every operation can significantly slow down your application,
     * especially if the amount of data is large. A more efficient solution might be to only write the data back
     * into files at certain intervals or during an application shutdown process
     */
    public void writeToFile() {
        ObjectMapper mapper = new ObjectMapper();
        List<User> userList = new ArrayList<>(userMap.values());
        try {
            mapper.writeValue(new File("users.json"), userList);
        } catch (IOException e) {
            throw new RuntimeException("Error writing data to file", e);
        }
    }

    @PostConstruct
    public void initTicketsData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Ticket[] tickets = mapper.readValue(new File(pathToTickets), Ticket[].class);
            for (Ticket ticket : tickets) {
                ticketMap.put(ticket.getUserId(), ticket);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error initializing data from file", e);
        }
        log.info("Init Tickets data");
    }

    @PostConstruct
    public void initEventsData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Event[] events = mapper.readValue(new File(pathToEvents), Event[].class);
            for (Event event : events) {
                eventMap.put(event.getEventId(), event);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error initializing data from file", e);
        }
        log.info("Init Events Data");
    }

}
