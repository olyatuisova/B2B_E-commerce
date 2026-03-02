public abstract class Payment implements Payable {

  protected double amount;
  protected String status;
  protected Order order;

  public Payment(Order order, double amount) {
    this.order = order;
    this.amount = amount;
    this.status = "PENDING";
  }

  @Override
  public double getAmount() { return amount; }

  @Override
  public String getPaymentStatus() { return status; }

  public final void execute() {
    System.out.println("Start of payment: " + amount + " uah");
    if (processPayment(amount)) {
      status = "PAID";
      order.setStatus("CONFIRMED");
      System.out.println("Payment successful!");
    } else {
      status = "FAILED";
      System.out.println("Payment declined");
    }
  }
}