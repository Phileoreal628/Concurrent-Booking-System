package com.bookingsystem.concurrentbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class BookingResponse {
    private boolean success;
    private String message;
    private UUID bookingId;
}
