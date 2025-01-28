package com.bookingsystem.concurrentbooking.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID bookingId;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Show show;

    @Column(nullable = false)
    private String bookingTime;

    @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
    private List<BookingSeat> bookingSeats;
}
