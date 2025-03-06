package com.bookingsystem.concurrentbooking.repository;

import com.bookingsystem.concurrentbooking.entity.CinemaHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CinemaHallRepository extends JpaRepository<CinemaHall, UUID> {
}
