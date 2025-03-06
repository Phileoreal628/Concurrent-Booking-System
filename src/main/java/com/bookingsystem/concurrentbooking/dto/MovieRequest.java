package com.bookingsystem.concurrentbooking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequest {
    private String title;
    private String genre;
    private int duration;
    private String language;
}
