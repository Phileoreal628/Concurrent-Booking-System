package com.bookingsystem.concurrentbooking.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "cinemaHall")
public class CinemaHall {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID hallId;

    @Column(nullable = false)
    private String hallName;

    @ManyToOne
    @JoinColumn(nullable = false, name = "city_id")
    private City city;

}
