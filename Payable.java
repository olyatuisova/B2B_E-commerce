public interface Payable {
    boolean processPayment(double amount);

    double getAmount();

    String getPaymentStatus();
    double getAmmount();
}
