import java.util.ArrayList;
import java.util.List;

public class Order {


    public static class OrderItem {
        private String productName;
        private String productType;
        private int quantity;
        private double pricePerUnit;

        public OrderItem(Product product, int quantity, double pricePerUnit) {
            this.productName = product.getName();
            this.productType = product.getProductType();
            this.quantity = quantity;
            this.pricePerUnit = pricePerUnit;
        }

        public double getSubtotal() { return pricePerUnit * quantity; }

        public void print() {
            System.out.println("  - [" + productType + "] " + productName
                    + " x" + quantity
                    + "  price" + pricePerUnit + " uah"
                    + " = " + getSubtotal() + " uah");
        }
    }

    private static int counter = 0;
    private int id;
    private Customer customer;
    private List<OrderItem> items;
    private String status;
    private String paymentMethod;

    public Order(Customer customer) {
        this.id = ++counter;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = "pending...";
    }

    public void fillFromCart(Cart cart) {
        for (Cart.CartItem cartItem : cart.getItems()) {
            double price = cartItem.getProduct().calculateFinalPrice(customer);
            items.add(new OrderItem(
                    cartItem.getProduct(),
                    cartItem.getQuantity(),
                    price));
        }
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void setStatus(String status) {
        this.status = status;
        System.out.println("Order " + id + " status: " + status);
    }

    public void setPaymentMethod(String method) {
        this.paymentMethod = method;
    }

    public void confirm() {
        if (items.isEmpty()) {
            System.out.println("Cannot confirm empty order!");
            return;
        }
        setStatus("Order confirmed");
    }

    public void printOrder() {
        System.out.println("Order " + id);
        System.out.println("Customer: " + customer.getCompanyName());
        System.out.println("Status:   " + status);
        System.out.println("Payment:  " + (paymentMethod != null ? paymentMethod : "not set"));
        System.out.println("Items: ");
        for (OrderItem item : items) {
            item.print();
        }
        System.out.println("Total: " + getTotal() + " uah");
    }

    public int getId() { return id; }
    public String getStatus() { return status; }
}