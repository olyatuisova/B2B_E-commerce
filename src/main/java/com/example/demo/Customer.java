package com.example.demo;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private String companyName;
    private String edrpou;
    private double creditLimit;
    public List<Order> orderHistory;
    private Cart cart;

    public Customer(int id, String name, String email,
                    String password, String companyName, String edrpou) {
        super(id, name, email, password);
        this.companyName = companyName;
        this.edrpou = edrpou;
        this.creditLimit = 0;
        this.orderHistory = new ArrayList<>();
        this.cart = new Cart();
    }

    @Override
    public String getRole() { return "CUSTOMER"; }

    @Override
    public void showDashboard() {
        System.out.println("Buyers panel: " + companyName);
        System.out.println("Credit limit: " + creditLimit + " uah");
        System.out.println("Count of orders: " + orderHistory.size());
    }

    public void addToCart(Product product, int quantity) {
        cart.addItem(product, quantity);
    }

    public void printCart() {
        cart.printCart(this);
    }
    public Cart getCart() { return cart; }

    public double getCreditLimit() { return creditLimit; }
    public void setCreditLimit(double limit) { this.creditLimit = limit; }
    public String getCompanyName() { return companyName; }
}