package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Event {
    private long eventId;
    private String eventName;
    private LocalDateTime eventDateTime;
}
