package com.bookingsystem.concurrentbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "show")
@NoArgsConstructor
@AllArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID showId;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String endTime;
}
