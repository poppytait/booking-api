package com.poppytait.bookingapi.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"FITNESS_CLASS_ID", "USER_ID"})
})
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

    public Booking(Long id, FitnessClass fitnessClass, User user) {
        this.id = id;
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
