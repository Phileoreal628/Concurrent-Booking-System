package com.bookingsystem.concurrentbooking.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID seatId;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private String seatType;
}
