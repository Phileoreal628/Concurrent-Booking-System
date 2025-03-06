package com.bookingsystem.concurrentbooking.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ShowRequest {
    private UUID movieId;
    private UUID hallId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double price;
}
