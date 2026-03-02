
public abstract class Product implements Discountable {

    private int id;
    private String name;
    private double basePrice;
    private String category;
    protected int stock;

    public Product(int id, String name, double basePrice, String category) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.category = category;
    }

    public double getBasePrice() { return basePrice; }
    public String getName() { return name; }
    public int getId() { return id; }

    public abstract double calculateFinalPrice(Customer customer);
    public abstract String getProductType();

    public void displayInfo() {
        System.out.println("order " + name + " — " + basePrice + " uah");
    }

    public void displayInfo(boolean showStock) {
        displayInfo();
        if (showStock) {
            System.out.println("In stock: " + stock + " p.");
        }
    }

    public void displayInfo(Customer customer) {
        displayInfo();
        System.out.println(" Your price: " +
                calculateFinalPrice(customer) + " uah");
    }

    @Override
    public double applyDiscount(double price) {
        return price;
    }
}