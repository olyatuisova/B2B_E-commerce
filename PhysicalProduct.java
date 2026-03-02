public class PhysicalProduct extends Product implements Shippable {

    private double weight;
    private int minOrderQty;
    private String trackingNumber;
    private String shipStatus;

    public PhysicalProduct(int id, String name, double price, String category, double weight, int minOrderQty) {
        super(id, name, price, category);
        this.weight = weight;
        this.minOrderQty = minOrderQty;
    }

    @Override
    public double calculateFinalPrice(Customer customer) {
        double price = getBasePrice();
        if (customer.getCreditLimit() > 500_000) {
            price *= 0.75;
        } else if (customer.getCreditLimit() > 100_000) {
            price *= 0.85;
        }
        return price;
    }

    @Override
    public String getProductType() { return "Physical product"; }

    @Override
    public double calculateShippingCost() {
        return weight * 15; // 15 грн/кг
    }

    @Override
    public double calculateShippingPrice() {

        return calculateShippingCost() * 1.20; // тут використовується 20% пдв
    }

    @Override
    public String getTrackingNumber() { return trackingNumber; }

    @Override
    public void updateStatus(String status) {
        this.shipStatus = status;
        System.out.println("Status: " + status);
    }


    @Override
    public String getDiscountType() { return "B2B volume discount"; }
}