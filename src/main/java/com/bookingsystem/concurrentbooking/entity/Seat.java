package com.bookingsystem.concurrentbooking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "seat")
@Getter
@Setter
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID seatId;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private String seatType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hallId", nullable = false)
    private CinemaHall cinemaHall;

    @Column(nullable = false)
    private double price;
}
