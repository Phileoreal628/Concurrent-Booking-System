package com.bookingsystem.concurrentbooking.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "bookingSeat")
public class BookingSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id",nullable = false)
    private Booking booking;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_id",nullable = false)
    private Seat seat;
}
