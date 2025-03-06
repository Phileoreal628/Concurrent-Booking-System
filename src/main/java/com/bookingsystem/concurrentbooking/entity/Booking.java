package com.bookingsystem.concurrentbooking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Booking_Movie")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID bookingId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Show show;

    @Column(nullable = false)
    private String bookingTime;

    @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingSeat> bookingSeats;
}
