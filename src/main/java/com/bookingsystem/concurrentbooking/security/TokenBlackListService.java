package com.bookingsystem.concurrentbooking.security;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenBlackListService {

    private final RedisTemplate<String, String> redisTemplate;

    private static final long EXPIRATION_TIME = 3600;

    public void blackListToken(String token) {
        redisTemplate.opsForValue().set(token,"blackListed",EXPIRATION_TIME, TimeUnit.SECONDS);
    }

    public boolean isBlackListed(String token) {
        return redisTemplate.hasKey(token);
    }
}
