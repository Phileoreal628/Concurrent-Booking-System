package com.bookingsystem.concurrentbooking.repository;

import com.bookingsystem.concurrentbooking.entity.BookingSeat;
import com.bookingsystem.concurrentbooking.entity.Seat;
import com.bookingsystem.concurrentbooking.entity.Show;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BookingSeatRepository extends JpaRepository<BookingSeat, UUID> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select bs from BookingSeat bs join bs.booking b where b.show = :show and bs.seat IN :seats")
    List<BookingSeat> findByShowAndSeatIn(@Param("show") Show show,@Param("seats") List<Seat> seat);
}
