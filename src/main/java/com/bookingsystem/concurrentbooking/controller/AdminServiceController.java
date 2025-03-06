package com.bookingsystem.concurrentbooking.controller;

import com.bookingsystem.concurrentbooking.dto.*;
import com.bookingsystem.concurrentbooking.entity.*;
import com.bookingsystem.concurrentbooking.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminServiceController {

    private final AdminService adminService;

    @PostMapping("/addCity")
    public ResponseEntity<City> addCity(@RequestBody CityRequest cityRequest) {
        City city = adminService.addCity(cityRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(city);
    }
    @PostMapping("/addCinemaHall")
    public ResponseEntity<CinemaHall> addCinemaHall(@RequestBody CinemaHallRequest cinemaHallRequest) {
        CinemaHall cinemaHall = adminService.addCinemaHall(cinemaHallRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(cinemaHall);
    }
    @PostMapping("/addSeat")
    public ResponseEntity<Seat> addSeat(@RequestBody SeatRequest seatRequest) {
        Seat seat = adminService.addSeat(seatRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(seat);
    }
    @PostMapping("/addMovie")
    public ResponseEntity<Movie> addMovie(@RequestBody MovieRequest movieRequest) {
        Movie movie = adminService.addMovie(movieRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }
    @PostMapping("/addShow")
    public ResponseEntity<Show> addShow(@RequestBody ShowRequest showRequest) {
        Show show = adminService.addShow(showRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(show);
    }
}
