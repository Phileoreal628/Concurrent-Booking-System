package com.bookingsystem.concurrentbooking.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CinemaHallRequest {

    private String hallName;
    private UUID cityId;
    private int totalSeats;

}
