package com.bookingsystem.concurrentbooking.entity;

public enum BookingStatus {
    RESERVED,  // Seat is temporarily reserved (during payment)
    CONFIRMED, // Seat is booked after successful payment
    CANCELED   // Seat was reserved but not paid for
}