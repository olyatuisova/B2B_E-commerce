package com.example.demo;
public class CardPayment extends Payment {

    private String cardNumber;
    private String cvv;

    public CardPayment(Order order, double amount,
                       String cardNumber, String cvv) {
        super(order, amount);
        this.cardNumber = maskCard(cardNumber);
        this.cvv = cvv;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("By card" + cardNumber);
        return true;
    }



    private String maskCard(String card) {
        return "**** **** ****" + card.substring(card.length() - 4);
    }
}