package com.bookingsystem.concurrentbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    private UUID userId;
    private UUID showId;
    private List<UUID> seatIds;
    private PaymentRequest paymentRequest;

}
