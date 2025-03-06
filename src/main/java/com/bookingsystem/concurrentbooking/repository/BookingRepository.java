package com.bookingsystem.concurrentbooking.repository;

import com.bookingsystem.concurrentbooking.entity.Booking;
import com.bookingsystem.concurrentbooking.entity.Seat;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookingRepository extends JpaRepository<Booking, UUID> {

    @Query("SELECT b from Booking b JOIN b.bookingSeats bs where bs.seat.seatId = :seatId AND b.show.showId = :showId")
    Optional<Booking> findBookingBySeatAndShow(@Param("seatId") UUID seatId,@Param("showId") UUID showId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b from Booking b where b.bookingId = :bookingId")
    Optional<Booking> findByIdForUpdate(@Param("bookingId") UUID bookingId);
}
