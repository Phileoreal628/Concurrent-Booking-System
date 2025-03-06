package com.bookingsystem.concurrentbooking.service;

import com.bookingsystem.concurrentbooking.dto.BookingResponse;
import com.bookingsystem.concurrentbooking.dto.PaymentRequest;
import com.bookingsystem.concurrentbooking.entity.*;
import com.bookingsystem.concurrentbooking.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;
    private final SeatReservationService seatReservationService;
    private final PaymentService paymentService;
    private final BookingSeatRepository bookingSeatRepository;

    @Transactional
    public BookingResponse initiateBooking(UUID userId, UUID showId , List<UUID> seatIds, PaymentRequest paymentRequest) {
        boolean reserveSeats = seatReservationService.reserveSeats(showId,seatIds,userId);
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        List<Seat> seats = seatRepository.findAllById(seatIds);
        if(!reserveSeats){
            return new BookingResponse(false,"Some seats are already booked",new UUID(0L, 0L));
        }
        // Recheck seat availability directly from the DB before proceeding
        List<BookingSeat> alreadyBookedSeats = bookingSeatRepository.findByShowAndSeatIn(show, seats);
        if (!alreadyBookedSeats.isEmpty()) {
            return new BookingResponse(false, "Some seats are already booked. Please choose different seats.",new UUID(0L, 0L));
        }
        try{
            boolean paymentSuccess = paymentService.processPayment(paymentRequest);
            if(paymentSuccess){
                Booking booking = confirmBooking(show,seats,user);
                seatReservationService.releaseSeats(showId,seatIds);
                return new BookingResponse(true,"Booking successful",booking.getBookingId());
            }else{
                seatReservationService.releaseSeats(showId,seatIds);
                return new BookingResponse(false,"Booking failed",new UUID(0L, 0L));
            }
        } catch (Exception e) {
            seatReservationService.releaseSeats(showId,seatIds);
            throw new RuntimeException("Error occurred while processing your booking.",e);
        }
    }

    @Transactional
    public Booking confirmBooking(Show show, List<Seat> seats, User user) {
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setBookingTime(LocalDateTime.now().toString());
        List<BookingSeat> bookingSeats = seats.stream().map(seat -> {
            BookingSeat bookingSeat = new BookingSeat();
            bookingSeat.setSeat(seat);
            bookingSeat.setBooking(booking);
            bookingSeat.setStatus(BookingStatus.CONFIRMED);
            bookingSeat.setReservedAt(LocalDateTime.now());
            return bookingSeat;
        }).collect(Collectors.toList());

        booking.setBookingSeats(bookingSeats);
        return bookingRepository.save(booking);

    }
}
