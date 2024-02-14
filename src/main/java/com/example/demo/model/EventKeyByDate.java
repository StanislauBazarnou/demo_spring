package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Objects;

public record EventKeyByDate(LocalDateTime eventDateTime) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventKeyByDate that = (EventKeyByDate) o;
        return eventDateTime.equals(that.eventDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventDateTime);
    }
}
