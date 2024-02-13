package com.example.demo.model;

public record UserKey(long id) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserKey key)) return false;
        return id == key.id;
    }
}
