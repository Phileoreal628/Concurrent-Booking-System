package com.bookingsystem.concurrentbooking.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "show")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID showId;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String endTime;
}
