package com.bookingsystem.concurrentbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "show")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID showId;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hall_id" , nullable = false)
    private CinemaHall cinemaHall;

    @Column(nullable = false)
    private double pricePerSeat;
}
