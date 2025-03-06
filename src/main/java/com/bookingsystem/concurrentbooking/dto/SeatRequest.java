package com.bookingsystem.concurrentbooking.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SeatRequest {
    private UUID hallId;
    private double price;
    private String seatType;
    private String seatNumber;
}
