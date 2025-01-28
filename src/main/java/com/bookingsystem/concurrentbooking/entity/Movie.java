package com.bookingsystem.concurrentbooking.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID movieId;

    @Column(nullable = false )
    private String title;

    @Column(nullable = false )
    private  String genre;

    private int duration;

    private String language;

}
