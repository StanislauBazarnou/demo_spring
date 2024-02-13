package com.example.demo.model;

public record TicketKey(long id) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketKey key)) return false;
        return id == key.id;
    }

}
