package com.poppytait.bookingapi.model;

import javax.persistence.*;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private FitnessClass fitnessClass;
    @OneToOne
    private User user;

    public Booking() {}

    public Booking(FitnessClass fitnessClass, User user) {
        this.fitnessClass = fitnessClass;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public FitnessClass getFitnessClass() {
        return fitnessClass;
    }

    public User getUser() {
        return user;
    }
}
