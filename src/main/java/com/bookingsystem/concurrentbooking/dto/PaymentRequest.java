package com.bookingsystem.concurrentbooking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private String cardHolderName;
    private String cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String cvv;
    private double amount;
}
