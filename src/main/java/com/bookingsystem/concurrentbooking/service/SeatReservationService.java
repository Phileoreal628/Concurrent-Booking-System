package com.bookingsystem.concurrentbooking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class SeatReservationService {

    private final RedisTemplate<String, String> redisTemplate;
    private static final long  RESERVATION_EXPIRY= 300; // 5 min

    public boolean reserveSeats(UUID showId, List<UUID> seatIds , UUID userId){

        List<String> reservedSeatIds = new ArrayList<>();

        try {
            for (UUID seatId : seatIds) {
                String seatKey = "reservation:" + showId + ":" + seatId;

                if (Boolean.TRUE.equals(redisTemplate.hasKey(seatKey))) {
                    releaseSeatsByKey(reservedSeatIds);
                    return false;
                }

                redisTemplate.opsForValue().set(seatKey, userId.toString(), RESERVATION_EXPIRY, TimeUnit.SECONDS);
                reservedSeatIds.add(seatKey);
            }
            return true;
        }catch (Exception e){
            releaseSeatsByKey(reservedSeatIds);
            throw new RuntimeException("Error reserving seats");
        }

    }
    private void releaseSeatsByKey(List<String> seatKeys){

        if(!seatKeys.isEmpty()){
            redisTemplate.delete(seatKeys);
        }

    }
    public void releaseSeats(UUID showId, List<UUID> seatIds){
        for (UUID seatId : seatIds) {
            String seatKey = "reservation:"+showId+":"+seatId;
            redisTemplate.delete(seatKey);
        }
    }
}
