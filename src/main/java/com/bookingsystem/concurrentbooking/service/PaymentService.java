package com.bookingsystem.concurrentbooking.service;

import com.bookingsystem.concurrentbooking.dto.PaymentRequest;
import com.bookingsystem.concurrentbooking.entity.Booking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    public boolean processPayment(PaymentRequest paymentRequest) {
        return Math.random() > .1;
    }
}
