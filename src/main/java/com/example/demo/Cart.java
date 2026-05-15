package com.example.demo;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    public class CartItem {
        private Product product;
        private int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() { return product; }
        public int getQuantity() { return quantity; }

        public void setQuantity(int quantity) {
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be > 0");
            }
            this.quantity = quantity;
        }
        public double getSubtotal(Customer customer) {
            return product.calculateFinalPrice(customer) * quantity;
        }

        public void print(Customer customer) {
            System.out.println(" - " + product.getName()
                    + " x" + quantity
                    + " = " + getSubtotal(customer) + " uah");
        }
    }

    private List<CartItem> items;


    private MyLinkedList<String> addedProductsHistory;

    public Cart() {
        this.items = new ArrayList<>();
        this.addedProductsHistory = new MyLinkedList<>();
    }

    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println("Updated cart: " + product.getName()
                        + " x" + item.getQuantity());

                addedProductsHistory.add(product.getName());
                return;
            }
        }
        items.add(new CartItem(product, quantity));
        addedProductsHistory.add(product.getName());
        System.out.println("Added to cart: " + product.getName() + " x" + quantity);
    }

    public void removeItem(Product product) {
        items.removeIf(item -> item.getProduct().getId().equals(product.getId()));
        System.out.println("Removed from cart: " + product.getName());
    }

    public double getTotal(Customer customer) {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal(customer);
        }
        return total;
    }

    public int getItemCount() { return items.size(); }
    public boolean isEmpty() { return items.isEmpty(); }

    public void clear() {
        items.clear();

        this.addedProductsHistory = new MyLinkedList<>();
        System.out.println("Cart cleared.");
    }

    public void printCart(Customer customer) {
        if (isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("~ Cart for " + customer.getCompanyName() + " ~");
        for (CartItem item : items) {
            item.print(customer);
        }
        System.out.println("  Total: " + getTotal(customer) + " uah");
        System.out.println("\nMyLinkedList:");
        addedProductsHistory.print();
    }

    public List<CartItem> getItems() { return items; }
}