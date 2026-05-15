package com.example.demo;

public interface Payable {
    boolean processPayment(double amount);

    double getAmount();

    String getPaymentStatus();
}
