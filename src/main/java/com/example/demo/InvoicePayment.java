
package com.example.demo;
public class InvoicePayment extends Payment {

    private int paymentTermDays;
    private String invoiceNumber;

    public InvoicePayment(Order order, double amount, int termDays) {
        super(order, amount);
        this.paymentTermDays = termDays;
        this.invoiceNumber = generateInvoice();
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Invoice " + invoiceNumber);
        System.out.println("Payment within: " + paymentTermDays + " days");
        return true;
    }


    private String generateInvoice() {
        return "INVOICE-" + System.currentTimeMillis();
    }
}