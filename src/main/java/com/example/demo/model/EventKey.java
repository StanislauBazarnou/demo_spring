package com.example.demo.model;

public record EventKey(long id) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventKey key)) return false;
        return id == key.id;
    }
}
