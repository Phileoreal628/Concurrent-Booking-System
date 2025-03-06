package com.bookingsystem.concurrentbooking.controller;

import com.bookingsystem.concurrentbooking.dto.BookingRequest;
import com.bookingsystem.concurrentbooking.dto.BookingResponse;

import com.bookingsystem.concurrentbooking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/createBooking")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest bookingRequest) {

        BookingResponse bookingResponse = bookingService.initiateBooking(
                bookingRequest.getUserId(),
                bookingRequest.getShowId(),
                bookingRequest.getSeatIds(),
                bookingRequest.getPaymentRequest());

        if(bookingResponse.isSuccess()){
            return ResponseEntity.ok(bookingResponse);
        }else{
            return ResponseEntity.badRequest().body(bookingResponse);
        }

    }
}
