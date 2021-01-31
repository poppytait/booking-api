package com.poppytait.bookingapi.model;

import java.time.Instant;

public class FitnessClass {
    private long id;
    private String name;
    private String instructor;
    private Instant startsAt;
    private Instant endsAt;
    private String location;
    private int capacity;

    public FitnessClass(long id, String name, String instructor, Instant startsAt, Instant endsAt, String location, int capacity) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.location = location;
        this.capacity = capacity;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInstructor() {
        return instructor;
    }

    public Instant getStartsAt() {
        return startsAt;
    }

    public Instant getEndsAt() {
        return endsAt;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }
}
